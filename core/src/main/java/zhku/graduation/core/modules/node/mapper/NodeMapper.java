package zhku.graduation.core.modules.node.mapper;

import zhku.graduation.core.modules.node.entity.po.Node;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 鱼缸节点信息表 Mapper 接口
 * </p>
 *
 * @author QR
 * @since 2022-01-29
 */
@Mapper
public interface NodeMapper extends BaseMapper<Node> {

}
