package zhku.graduation.core.modules.user.mapper;

import zhku.graduation.core.modules.user.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author QR
 * @since 2021-12-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
