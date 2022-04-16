package zhku.graduation.core.modules.limit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostDetail;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostListInfo;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostListRequest;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostPageRequest;
import zhku.graduation.core.modules.limit.entity.po.CommandRecordHost;

import java.util.List;

/**
 * <p>
 * 上位机 端控制命令记录服务类
 * </p>
 *
 * @author QR
 * @since 2022-04-16
 */
public interface ICommandRecordHostService extends IService<CommandRecordHost> {

    List<CommandRecordHostListInfo> getCommandRecordHostList(CommandRecordHostListRequest request);

    Page<CommandRecordHostDetail> pageCommandRecordHost(CommandRecordHostPageRequest request);

    CommandRecordHostDetail getCommandRecordHost(Integer dataId);

    boolean saveOrUpdateCommandRecordHost(CommandRecordHostDetail dto);

    boolean removeCommandRecordHost(Integer dataId);
}
