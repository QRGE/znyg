package zhku.graduation.core.modules.command.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.mapper.CommandRecordWebMapper;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


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
    public Integer saveOrUpdateCommandRecordWeb(String command, Constant.CommandObj obj) {
        CommandRecordWeb newCommand = new CommandRecordWeb();
        newCommand.setCommandText(command);
        newCommand.setCommandStatus(Constant.CommandStatus.HAD_SENT.getType());
        newCommand.setCommandObj(obj.name());
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

}
