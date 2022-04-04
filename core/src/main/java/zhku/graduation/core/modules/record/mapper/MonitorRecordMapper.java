package zhku.graduation.core.modules.record.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

import java.util.Date;

/**
 * <p>
 * 监测记录表 Mapper 接口
 * </p>
 *
 * @author QR
 * @since 2022-02-14
 */
@Mapper
public interface MonitorRecordMapper extends BaseMapper<MonitorRecord> {

    int countMonitorRecords(@Param("nodeId") Integer nodeId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
