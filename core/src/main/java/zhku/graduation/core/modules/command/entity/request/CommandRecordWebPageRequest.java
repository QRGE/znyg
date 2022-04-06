package zhku.graduation.core.modules.command.entity.request;

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
 * @since 2022-03-01
 */
@Getter
@Setter
@ToString
@ApiModel(value = "Web 端控制命令记录分页请求")
public class CommandRecordWebPageRequest extends BasePageRequest implements BaseTimeRequest {

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("排序类型, 1-正序, 2-倒序")
    private Integer orderType;

}
