package zhku.graduation.core.modules.command.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.mapper.CommandRecordWebMapper;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;

import java.util.Optional;


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
    public CommandRecordWebDetail getCommandRecordWeb() {
        CommandRecordWeb commandRecordWeb = getOne(Wrappers.lambdaQuery(CommandRecordWeb.class)
                .orderByDesc(CommandRecordWeb::getCreateTime)
                .last("limit 1"));
        return Optional.ofNullable(commandRecordWeb)
                        .map(po -> new CommandRecordWebDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdateCommandRecordWeb(String command) {
        CommandRecordWeb newCommand = new CommandRecordWeb();
        newCommand.setCommandText(command);
        newCommand.setCommandStatus(Constant.CommandStatus.HAD_SENT.getType());
        return save(newCommand);
    }

    @Override
    public boolean removeCommandRecordWeb(Integer dataId) {
        return removeById(dataId);
    }

}
