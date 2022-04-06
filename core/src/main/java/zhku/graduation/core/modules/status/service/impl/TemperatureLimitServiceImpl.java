package zhku.graduation.core.modules.status.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.status.entity.bean.request.CommandRequest;
import zhku.graduation.core.modules.status.entity.po.NodeStatus;
import zhku.graduation.core.modules.status.mapper.TemperatureLimitMapper;
import zhku.graduation.core.modules.status.service.ITemperatureLimitService;
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
public class TemperatureLimitServiceImpl extends ServiceImpl<TemperatureLimitMapper, NodeStatus> implements ITemperatureLimitService {

    @Autowired
    private ICommandRecordWebService commandRecordWebService;

    @Override
    public List<TemperatureLimitListInfo> getTemperatureLimitList(TemperatureLimitListRequest request) {
        LambdaQueryWrapper<NodeStatus> queryWrapper = baseQueryWrapper();
        List<NodeStatus> poList = list(queryWrapper);
        List<TemperatureLimitListInfo> dtoList = new ArrayList<>(poList.size());
        if (!poList.isEmpty()) {
            dtoList = poList.stream().map(TemperatureLimitListInfo::new).collect(Collectors.toList());
        }
        return dtoList;
    }

    @Override
    public TemperatureLimitDetail getTemperatureLimit(Integer nodeId) {
        LambdaUpdateWrapper<NodeStatus> wrapper = Wrappers.lambdaUpdate(NodeStatus.class)
                .eq(NodeStatus::getNodeId, nodeId);
        NodeStatus nodeStatus = getOne(wrapper);
        return Optional.ofNullable(nodeStatus)
                        .map(po -> new TemperatureLimitDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public Integer saveOrUpdateTemperatureLimit(TemperatureLimitDetail dto) {
        NodeStatus nodeStatus = new NodeStatus();
        LambdaUpdateWrapper<NodeStatus> updateWrapper = Wrappers.lambdaUpdate(NodeStatus.class)
                .eq(NodeStatus::getNodeId, dto.getNodeId());
        nodeStatus = nodeStatus.parseFromDTO(dto);
        saveOrUpdate(nodeStatus, updateWrapper);
        // 记录控制命令
        String command = CommandUtil.createTemperatureLimitCommand(dto.getNodeId(), dto.getTemperatureUpperLimit(), dto.getTemperatureLowerLimit());
        // 保存控制命令
        return commandRecordWebService.saveOrUpdateCommandRecordWeb(command, Constant.CommandObj.J, dto.getNodeId());
    }

    @Override
    public boolean removeTemperatureLimit(Integer dataId) {
        return removeById(dataId);
    }

    @Override
    public Integer saveCommand(CommandRequest request) {
        Integer nodeId = request.getNodeId();
        String commandObj = request.getCommandObj();
        LambdaQueryWrapper<NodeStatus> wrapper = baseQueryWrapper()
                .eq(NodeStatus::getNodeId, nodeId);
        NodeStatus nodeStatus = getOne(wrapper);
        Constant.CommandObj obj = Constant.CommandObj.valueOf(commandObj);
        Integer status = request.getStatus();
        switch (obj) {
            case J:
                nodeStatus.setHeaterStatus(status);
                updateById(nodeStatus);
                break;
            case C:
                nodeStatus.setDegermingStatus(status);
                updateById(nodeStatus);
                break;
            case D:
                nodeStatus.setLightStatus(status);
                updateById(nodeStatus);
                break;
        }
        return commandRecordWebService.saveOrUpdateCommandRecordWeb(CommandUtil.createHeaterCommand(nodeId, status, commandObj), obj, nodeId);
    }

    private LambdaQueryWrapper<NodeStatus> baseQueryWrapper() {
        return Wrappers.lambdaQuery(NodeStatus.class)
                        .eq(NodeStatus::getIsDel, 0);
    }

    private LambdaUpdateWrapper<NodeStatus> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(NodeStatus.class)
                        .eq(NodeStatus::getIsDel, 0);
    }
}
