package zhku.graduation.core.modules.user.entity.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.user.entity.po.User;

import javax.validation.constraints.NotEmpty;

/**
 * @author qr
 * @date 2022/1/15 12:50
 */
@ApiOperation("登陆信息")
@Getter
@Setter
public class LoginUser {

    @ApiModelProperty("账户, 目前为用户名或邮箱")
    @NotEmpty(message = "账号不能为空")
    private String account;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    public LoginUser parseFromPO(User user) {
        account = user.getUsername();
        password = user.getPassword();
        return this;
    }
}
