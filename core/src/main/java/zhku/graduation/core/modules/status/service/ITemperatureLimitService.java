package zhku.graduation.core.modules.status.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.status.entity.bean.request.CommandRequest;
import zhku.graduation.core.modules.status.entity.po.NodeStatus;

import java.util.List;

/**
 * <p>
 * 鱼缸温度限制服务类
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
public interface ITemperatureLimitService extends IService<NodeStatus> {

    List<TemperatureLimitListInfo> getTemperatureLimitList(TemperatureLimitListRequest request);

    TemperatureLimitDetail getTemperatureLimit(Integer nodeId);

    Integer saveOrUpdateTemperatureLimit(TemperatureLimitDetail dto);

    boolean removeTemperatureLimit(Integer dataId);

    Integer saveCommand(CommandRequest request);
}
