package zhku.graduation.core.modules.limit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.limit.entity.po.TemperatureLimit;

import java.util.List;

/**
 * <p>
 * 鱼缸温度限制服务类
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
public interface ITemperatureLimitService extends IService<TemperatureLimit> {

    List<TemperatureLimitListInfo> getTemperatureLimitList(TemperatureLimitListRequest request);

    TemperatureLimitDetail getTemperatureLimit(Integer nodeId);

    boolean saveOrUpdateTemperatureLimit(TemperatureLimitDetail dto);

    boolean removeTemperatureLimit(Integer dataId);
}
