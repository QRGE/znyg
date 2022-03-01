package zhku.graduation.core.modules.command.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebListInfo;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.mapper.CommandRecordWebMapper;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * Web 端控制命令记录 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-03-01
 */
@Service
public class CommandRecordWebServiceImpl extends ServiceImpl<CommandRecordWebMapper, CommandRecordWeb> implements ICommandRecordWebService {

    @Override
    public CommandRecordWebDetail getCommandRecordWeb(Integer dataId) {
        CommandRecordWeb commandRecordWeb = getById(dataId);
        return Optional.ofNullable(commandRecordWeb)
                        .map(po -> new CommandRecordWebDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdateCommandRecordWeb(CommandRecordWebDetail dto) {
        CommandRecordWeb commandRecordWeb = new CommandRecordWeb();
        if(dto.getId() == null){
            commandRecordWeb = commandRecordWeb.init();
        }
        commandRecordWeb.parseFromDto(dto);
        return saveOrUpdate(commandRecordWeb);
    }

    @Override
    public boolean removeCommandRecordWeb(Integer dataId) {
        return removeById(dataId);
    }

    @Override
    public Page<CommandRecordWebListInfo> pageCommandRecords(Integer nodeId, Date startTime, Date endTime, Integer orderType, Integer pageStart, Integer pageSize) {
        Page<CommandRecordWebListInfo> page = new Page<>();
        LambdaQueryWrapper<CommandRecordWeb> queryWrapper = Wrappers.lambdaQuery(CommandRecordWeb.class);
        if (startTime != null) {
            queryWrapper.ge(CommandRecordWeb::getCreateTime, startTime);
        }
        if (endTime != null) {
            queryWrapper.le(CommandRecordWeb::getCreateTime, endTime);
        }
        long count = count(queryWrapper);
        page.setCount(count);
        queryWrapper.last("limit " + pageSize+ " offset " + pageStart);
        if (orderType.equals(Constant.OrderType.DESC.getType())) {
            queryWrapper.orderByDesc(CommandRecordWeb::getCreateTime);
        }else {
            queryWrapper.orderByAsc(CommandRecordWeb::getCreateTime);
        }
        List<CommandRecordWeb> list = list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            List<CommandRecordWebListInfo> resultList = list.stream().map(CommandRecordWebListInfo::new)
                    .collect(Collectors.toList());
            page.setRecords(resultList);
        }
        Long totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        page.setTotalPage(totalPage);
        return page;
    }

}
