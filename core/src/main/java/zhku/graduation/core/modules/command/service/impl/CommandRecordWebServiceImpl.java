package zhku.graduation.core.modules.command.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.entity.request.CommandRecordWebPageRequest;
import zhku.graduation.core.modules.command.mapper.CommandRecordWebMapper;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;
import zhku.graduation.core.modules.node.service.INodeService;

import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * Web 端控制命令记录 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-03-01
 */
@Transactional
@Service
public class CommandRecordWebServiceImpl extends ServiceImpl<CommandRecordWebMapper, CommandRecordWeb> implements ICommandRecordWebService {

    @Autowired
    private INodeService nodeService;

    @Override
    public CommandRecordWebDetail getCommandRecordWeb() {
        CommandRecordWeb commandRecordWeb = getOne(Wrappers.lambdaQuery(CommandRecordWeb.class)
                .orderByDesc(CommandRecordWeb::getCreateTime)
                .last("limit 1"));
        return Optional.ofNullable(commandRecordWeb)
                        .map(po -> new CommandRecordWebDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public Integer saveOrUpdateCommandRecordWeb(String command, Constant.CommandObj obj, Integer nodeId) {
        CommandRecordWeb newCommand = new CommandRecordWeb();
        newCommand.setCommandText(command);
        newCommand.setCommandStatus(Constant.CommandStatus.HAD_SENT.getType());
        newCommand.setCommandObj(obj.name());
        newCommand.setNodeId(nodeId);
        save(newCommand);
        final Integer newId = newCommand.getId();
        // 5秒后查询状态
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 更新执行命令的状态
                CommandRecordWeb web = getById(newId);
                if (!web.getCommandStatus().equals(Constant.CommandStatus.FINISHED.getType())) {
                    web.setCommandStatus(Constant.CommandStatus.ERROR.getType());
                    updateById(web);
                }
            }
        }, 5*1000);
        return newId;
    }

    @Override
    public boolean removeCommandRecordWeb(Integer dataId) {
        return removeById(dataId);
    }

    @Override
    public Page<CommandRecordWebDetail> pageCommands(CommandRecordWebPageRequest request) {
        Page<CommandRecordWebDetail> page = new Page<>();
        LambdaQueryWrapper<CommandRecordWeb> wrapper = Wrappers.lambdaQuery(CommandRecordWeb.class);
        if (request.getNodeId() != null) {
            wrapper.eq(CommandRecordWeb::getNodeId, request.getNodeId());
        }
        List<Integer> nodeIds = nodeService.getNodeIds();
        wrapper.in(CommandRecordWeb::getNodeId, nodeIds);
        if (request.getStartTime() != null) {
            wrapper.ge(CommandRecordWeb::getCreateTime, request.getStartTime());
        }
        if (request.getEndTime() != null) {
            wrapper.le(CommandRecordWeb::getCreateTime, DateUtil.offset(request.getEndTime(), DateField.DAY_OF_MONTH, 1));
        }
        if (request.getOrderType().equals(Constant.OrderType.DESC.getType())) {
            wrapper.orderByDesc(CommandRecordWeb::getCreateTime);
        }else {
            wrapper.orderByAsc(CommandRecordWeb::getCreateTime);
        }
        long count = count(wrapper);
        Integer pageSize = request.getPageSize();
        wrapper.last("limit " + pageSize + " offset " + request.getPageStart());
        List<CommandRecordWeb> list = list(wrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            Map<Integer, String> idToName = nodeService.getIdToName();
            List<CommandRecordWebDetail> details = list.stream()
                    .map(p -> {
                        CommandRecordWebDetail command = new CommandRecordWebDetail().parseFromPo(p);
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

}
