package zhku.graduation.core.modules.record.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.basic.request.BasePageRequest;

/**
 * @author QR
 * @since 2022-02-14
 */
@Getter
@Setter
@ToString
@ApiModel(value = "监测记录表分页请求")
public class MonitorRecordPageRequest extends BasePageRequest{


}
