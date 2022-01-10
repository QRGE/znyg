package zhku.graduation.core.modules.user.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.basic.request.BasePageRequest;

/**
 * @author QR
 * @since 2022-01-10
 */
@Getter
@Setter
@ToString
@ApiModel(value = "用户表分页请求")
public class UserPageRequest extends BasePageRequest{


}
