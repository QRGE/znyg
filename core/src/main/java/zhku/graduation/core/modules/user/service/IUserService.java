package zhku.graduation.core.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.user.entity.bean.LoginUser;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;
import zhku.graduation.core.modules.user.entity.bean.UserListInfo;
import zhku.graduation.core.modules.user.entity.bean.UserPageRequest;
import zhku.graduation.core.modules.user.entity.po.User;

/**
 * <p>
 * 用户表服务类
 * </p>
 *
 * @author QR
 * @since 2022-01-10
 */
public interface IUserService extends IService<User> {

    /**
     * 校验用户的有效性
     * @param account 用户名
     * @param password 密码
     * @return 校验结果
     */
    boolean isValidUser(String account, String password);

    /**
     * 查看 username 是否有效
     */
    boolean isValidUser(String username);

    boolean isUserEmail(String username, String email);

    /**
     * 通过用户名获取 user 对象
     * @param username 用户名
     * @return user 对象
     */
    LoginUser getUser(String username);

    IPage<UserListInfo> pageUser(UserPageRequest request);

    UserDetail getUser(Integer dataId);

    UserDetail getUserByUsername(String  username);

    boolean saveOrUpdateUser(UserDetail dto);

    boolean removeUser(Integer dataId);

    /**
     * 新增用户
     * @param account 账号
     * @param password 密码
     * @return 操作结果
     */
    boolean addUser(String account, String password);

    /**
     * 更新密码
     */
    boolean updatePwd(String newPwd, String username);
}
