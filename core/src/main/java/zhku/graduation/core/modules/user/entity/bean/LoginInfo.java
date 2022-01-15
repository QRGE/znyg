package zhku.graduation.core.modules.user.entity.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qr
 * @date 2022/1/15 12:50
 */
@ApiOperation("登陆信息")
@Getter
@Setter
public class LoginInfo {

    @ApiModelProperty("账户, 目前为用户名或邮箱")
    private String account;

    @ApiModelProperty("密码")
    private String password;
}
