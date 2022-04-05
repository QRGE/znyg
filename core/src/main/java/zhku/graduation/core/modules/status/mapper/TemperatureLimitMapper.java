package zhku.graduation.core.modules.status.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import zhku.graduation.core.modules.status.entity.po.NodeStatus;

/**
 * <p>
 * 鱼缸温度限制 Mapper 接口
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
@Mapper
public interface TemperatureLimitMapper extends BaseMapper<NodeStatus> {

}
