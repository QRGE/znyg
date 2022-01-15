package zhku.graduation.core.modules.user.entity.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author qr
 * @date 2022/1/15 12:50
 */
@ApiOperation("登陆信息")
@Getter
@Setter
public class LoginInfo {

    @ApiModelProperty("账户, 目前为用户名或邮箱")
    @NotEmpty
    private String account;

    @ApiModelProperty("密码")
    @NotEmpty
    private String password;
}
