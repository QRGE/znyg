package zhku.graduation.core.modules.command.mapper;

import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Web 端控制命令记录 Mapper 接口
 * </p>
 *
 * @author QR
 * @since 2022-03-01
 */
@Mapper
public interface CommandRecordWebMapper extends BaseMapper<CommandRecordWeb> {

}
