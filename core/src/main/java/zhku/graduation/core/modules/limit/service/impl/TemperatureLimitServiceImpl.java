package zhku.graduation.core.modules.limit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.limit.entity.po.TemperatureLimit;
import zhku.graduation.core.modules.limit.mapper.TemperatureLimitMapper;
import zhku.graduation.core.modules.limit.service.ITemperatureLimitService;
import zhku.graduation.core.util.CommandUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * 鱼缸温度限制 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
@Transactional
@Service
public class TemperatureLimitServiceImpl extends ServiceImpl<TemperatureLimitMapper, TemperatureLimit> implements ITemperatureLimitService {

    @Autowired
    private ICommandRecordWebService commandRecordWebService;

    @Override
    public List<TemperatureLimitListInfo> getTemperatureLimitList(TemperatureLimitListRequest request) {
        LambdaQueryWrapper<TemperatureLimit> queryWrapper = baseQueryWrapper();
        List<TemperatureLimit> poList = list(queryWrapper);
        List<TemperatureLimitListInfo> dtoList = new ArrayList<>(poList.size());
        if (!poList.isEmpty()) {
            dtoList = poList.stream().map(TemperatureLimitListInfo::new).collect(Collectors.toList());
        }
        return dtoList;
    }

    @Override
    public TemperatureLimitDetail getTemperatureLimit(Integer nodeId) {
        LambdaUpdateWrapper<TemperatureLimit> wrapper = Wrappers.lambdaUpdate(TemperatureLimit.class)
                .eq(TemperatureLimit::getNodeId, nodeId);
        TemperatureLimit temperatureLimit = getOne(wrapper);
        return Optional.ofNullable(temperatureLimit)
                        .map(po -> new TemperatureLimitDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public Integer saveOrUpdateTemperatureLimit(TemperatureLimitDetail dto) {
        TemperatureLimit temperatureLimit = new TemperatureLimit();
        LambdaUpdateWrapper<TemperatureLimit> updateWrapper = Wrappers.lambdaUpdate(TemperatureLimit.class)
                .eq(TemperatureLimit::getNodeId, dto.getNodeId());
        temperatureLimit = temperatureLimit.parseFromDTO(dto);
        saveOrUpdate(temperatureLimit, updateWrapper);
        // 记录控制命令
        String command = CommandUtil.createTemperatureLimitCommand(dto.getNodeId(), dto.getTemperatureUpperLimit(), dto.getTemperatureLowerLimit());
        // 保存控制命令
        return commandRecordWebService.saveOrUpdateCommandRecordWeb(command, Constant.CommandObj.J);
    }

    @Override
    public boolean removeTemperatureLimit(Integer dataId) {
        return removeById(dataId);
    }

    private LambdaQueryWrapper<TemperatureLimit> baseQueryWrapper() {
        return Wrappers.lambdaQuery(TemperatureLimit.class)
                        .eq(TemperatureLimit::getIsDel, 0);
    }

    private LambdaUpdateWrapper<TemperatureLimit> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(TemperatureLimit.class)
                        .eq(TemperatureLimit::getIsDel, 0);
    }
}
