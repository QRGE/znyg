package zhku.graduation.core.modules.limit.mapper;

import zhku.graduation.core.modules.limit.entity.po.TemperatureLimit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 鱼缸温度限制 Mapper 接口
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
@Mapper
public interface TemperatureLimitMapper extends BaseMapper<TemperatureLimit> {

}
