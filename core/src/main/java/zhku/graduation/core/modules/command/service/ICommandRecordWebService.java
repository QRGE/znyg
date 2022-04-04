package zhku.graduation.core.modules.command.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;

/**
 * <p>
 * Web 端控制命令记录服务类
 * </p>
 *
 * @author QR
 * @since 2022-03-01
 */
public interface ICommandRecordWebService extends IService<CommandRecordWeb> {

    CommandRecordWebDetail getCommandRecordWeb();

    boolean saveOrUpdateCommandRecordWeb(String command);

    boolean removeCommandRecordWeb(Integer dataId);

}
