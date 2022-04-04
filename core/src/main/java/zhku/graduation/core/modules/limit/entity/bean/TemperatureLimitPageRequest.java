package zhku.graduation.core.modules.limit.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.basic.request.BasePageRequest;

/**
 * @author QR
 * @since 2022-04-04
 */
@Getter
@Setter
@ToString
@ApiModel(value = "鱼缸温度限制分页请求")
public class TemperatureLimitPageRequest extends BasePageRequest{


}
