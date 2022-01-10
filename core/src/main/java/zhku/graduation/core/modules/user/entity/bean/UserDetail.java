package zhku.graduation.core.modules.user.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.user.entity.po.User;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author QR
 * @since 2022-01-10
 */
@Getter
@Setter
@ApiModel(value = "用户表详情")
public class UserDetail {

    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("密码盐, 加密用")
    private String salt;

    @ApiModelProperty("性别, 1-男, 2-女")
    private Integer gender;

    @ApiModelProperty("手机号")
    private Integer phoneNumber;

    @ApiModelProperty("邮箱")
    private String eMail;

    @ApiModelProperty("角色, 1-管理员, 2-普通用户")
    private Integer role;


    @ApiModelProperty("创建者id")
    private Integer createBy;


    @ApiModelProperty("更新者id")
    private Integer updateBy;


    public UserDetail parseFromPo(User po) {
        id = po.getId();
        username = po.getUsername();
        password = po.getPassword();
        salt = po.getSalt();
        gender = po.getGender();
        phoneNumber = po.getPhoneNumber();
        eMail = po.getEMail();
        role = po.getRole();
        return this;
    }
}
