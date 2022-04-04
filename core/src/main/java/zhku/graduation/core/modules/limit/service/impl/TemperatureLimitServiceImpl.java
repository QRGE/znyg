package zhku.graduation.core.modules.limit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.limit.entity.po.TemperatureLimit;
import zhku.graduation.core.modules.limit.mapper.TemperatureLimitMapper;
import zhku.graduation.core.modules.limit.service.ITemperatureLimitService;

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
@Service
public class TemperatureLimitServiceImpl extends ServiceImpl<TemperatureLimitMapper, TemperatureLimit> implements ITemperatureLimitService {

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
    public TemperatureLimitDetail getTemperatureLimit(Integer dataId) {
        TemperatureLimit temperatureLimit = getById(dataId);
        return Optional.ofNullable(temperatureLimit)
                        .map(po -> new TemperatureLimitDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdateTemperatureLimit(TemperatureLimitDetail dto) {
        TemperatureLimit temperatureLimit = new TemperatureLimit();
        LambdaUpdateWrapper<TemperatureLimit> updateWrapper = Wrappers.lambdaUpdate(TemperatureLimit.class)
                .eq(TemperatureLimit::getNodeId, dto.getNodeId());
        temperatureLimit = temperatureLimit.parseFromDTO(dto);
        return saveOrUpdate(temperatureLimit, updateWrapper);
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
