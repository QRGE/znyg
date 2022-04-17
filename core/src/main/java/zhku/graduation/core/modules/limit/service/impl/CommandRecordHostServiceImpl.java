package zhku.graduation.core.modules.limit.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostDetail;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostListInfo;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostListRequest;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostPageRequest;
import zhku.graduation.core.modules.limit.entity.po.CommandRecordHost;
import zhku.graduation.core.modules.limit.mapper.CommandRecordHostMapper;
import zhku.graduation.core.modules.limit.service.ICommandRecordHostService;
import zhku.graduation.core.modules.node.service.INodeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * 上位机 端控制命令记录 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-04-16
 */
@Service
public class CommandRecordHostServiceImpl extends ServiceImpl<CommandRecordHostMapper, CommandRecordHost> implements ICommandRecordHostService {

    @Autowired
    private INodeService nodeService;

    @Override
    public List<CommandRecordHostListInfo> getCommandRecordHostList(CommandRecordHostListRequest request) {
        LambdaQueryWrapper<CommandRecordHost> queryWrapper = baseQueryWrapper();
        List<CommandRecordHost> poList = list(queryWrapper);
        List<CommandRecordHostListInfo> dtoList = new ArrayList<>(poList.size());
        if (!poList.isEmpty()) {
            dtoList = poList.stream().map(CommandRecordHostListInfo::new).collect(Collectors.toList());
        }
        return dtoList;
    }

    @Override
    public Page<CommandRecordHostDetail> pageCommandRecordHost(CommandRecordHostPageRequest request) {
        Page<CommandRecordHostDetail> page = new Page<>();
        LambdaQueryWrapper<CommandRecordHost> wrapper = Wrappers.lambdaQuery(CommandRecordHost.class);
        if (request.getNodeId() != null) {
            wrapper.eq(CommandRecordHost::getNodeId, request.getNodeId());
        }
        List<Integer> nodeIds = nodeService.getNodeIds();
        wrapper.in(CommandRecordHost::getNodeId, nodeIds);
        if (request.getStartTime() != null) {
            wrapper.ge(CommandRecordHost::getCreateTime, request.getStartTime());
        }
        if (request.getEndTime() != null) {
            wrapper.le(CommandRecordHost::getCreateTime, DateUtil.offset(request.getEndTime(), DateField.DAY_OF_MONTH, 1));
        }
        if (request.getOrderType().equals(Constant.OrderType.DESC.getType())) {
            wrapper.orderByDesc(CommandRecordHost::getCreateTime);
        }else {
            wrapper.orderByAsc(CommandRecordHost::getCreateTime);
        }
        long count = count(wrapper);
        Integer pageSize = request.getPageSize();
        wrapper.last("limit " + pageSize + " offset " + request.getPageStart());
        List<CommandRecordHost> list = list(wrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            Map<Integer, String> idToName = nodeService.getIdToName();
            List<CommandRecordHostDetail> details = list.stream()
                    .map(p -> {
                        CommandRecordHostDetail command = new CommandRecordHostDetail().parseFromPo(p);
                        command.setNodeName(idToName.get(p.getNodeId()));
                        return command;
                    })
                    .collect(Collectors.toList());
            page.setRecords(details);
        }
        Long totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        page.setTotalPage(totalPage);
        page.setCount(count);
        return page;
    }

    @Override
    public CommandRecordHostDetail getCommandRecordHost(Integer dataId) {
        CommandRecordHost commandRecordHost = getById(dataId);
        return Optional.ofNullable(commandRecordHost)
                        .map(po -> new CommandRecordHostDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdateCommandRecordHost(CommandRecordHostDetail dto) {
        CommandRecordHost commandRecordHost = new CommandRecordHost();
        if(dto.getId() == null){
            commandRecordHost = commandRecordHost.init();
        }
        commandRecordHost.parseFromDto(dto);
        return saveOrUpdate(commandRecordHost);
    }

    @Override
    public boolean removeCommandRecordHost(Integer dataId) {
        return removeById(dataId);
    }

    private LambdaQueryWrapper<CommandRecordHost> baseQueryWrapper() {
        return Wrappers.lambdaQuery(CommandRecordHost.class)
                        .eq(CommandRecordHost::getIsDel, 0);
    }

    private LambdaUpdateWrapper<CommandRecordHost> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(CommandRecordHost.class)
                        .eq(CommandRecordHost::getIsDel, 0);
    }
}
