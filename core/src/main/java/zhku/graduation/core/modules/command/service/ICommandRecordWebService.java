package zhku.graduation.core.modules.command.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebListInfo;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;

import java.util.Date;

/**
 * <p>
 * Web 端控制命令记录服务类
 * </p>
 *
 * @author QR
 * @since 2022-03-01
 */
public interface ICommandRecordWebService extends IService<CommandRecordWeb> {

    CommandRecordWebDetail getCommandRecordWeb(Integer dataId);

    boolean saveOrUpdateCommandRecordWeb(CommandRecordWebDetail dto);

    boolean removeCommandRecordWeb(Integer dataId);

    Page<CommandRecordWebListInfo> pageCommandRecords(Integer nodeId, Date startTime, Date endTime, Integer orderType, Integer pageStart, Integer pageSize);
}
