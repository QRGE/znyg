package zhku.graduation.core.modules.user.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author qr
 * @date 2022/3/8 16:30
 */
@ApiModel("更新密码对象")
@Getter
@Setter
public class UpdatePwdBean {

    @NotBlank
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank
    @ApiModelProperty("验证码")
    private String captcha;

    @NotBlank
    @ApiModelProperty("账号")
    private String username;

    @NotBlank
    @ApiModelProperty("新密码")
    private String newPwd;
}
