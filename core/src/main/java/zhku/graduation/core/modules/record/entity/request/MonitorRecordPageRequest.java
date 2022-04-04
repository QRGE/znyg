package zhku.graduation.core.modules.record.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.basic.request.BasePageRequest;
import zhku.graduation.basic.request.BaseTimeRequest;

import java.util.Date;

/**
 * @author QR
 * @since 2022-02-14
 */
@Getter
@Setter
@ToString
@ApiModel(value = "监测记录表分页请求")
public class MonitorRecordPageRequest extends BasePageRequest implements BaseTimeRequest {

    @ApiModelProperty("鱼缸节点id")
    private Integer nodeId;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("排序类型, 1-正序, 2-倒序")
    private Integer orderType;
}
