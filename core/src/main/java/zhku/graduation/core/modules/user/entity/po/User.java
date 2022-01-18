package zhku.graduation.core.modules.user.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;

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
@TableName("znyg_user")
@NoArgsConstructor
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 密码盐, 加密用
     */
    @TableField("salt")
    private String salt;

    /**
     * 性别, 1-男, 2-女
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 手机号
     */
    @TableField("phone_number")
    private Integer phoneNumber;

    /**
     * 邮箱
     */
    @TableField("e_mail")
    private String eMail;

    /**
     * 角色, 1-管理员, 2-普通用户
     */
    @TableField("role")
    private Integer role;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建者id
     */
    @TableField("create_by")
    private Integer createBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新者id
     */
    @TableField("update_by")
    private Integer updateBy;

    /**
     * 是否删除1-是 0-否
     */
    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public User parseFromDto(UserDetail dto) {
        id = dto.getId();
        username = dto.getUsername();
        gender = dto.getGender();
        phoneNumber = dto.getPhoneNumber();
        eMail = dto.getEMail();
        role = dto.getRole();
        return this;
    }


}
