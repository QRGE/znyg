package zhku.graduation.core.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;
import zhku.graduation.core.modules.user.entity.bean.UserListInfo;
import zhku.graduation.core.modules.user.entity.bean.UserListRequest;
import zhku.graduation.core.modules.user.entity.bean.UserPageRequest;
import zhku.graduation.core.modules.user.entity.po.User;

import java.util.List;

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
     * @param password
     * @return 校验
     */
    boolean checkUser(String account, String password);

    List<UserListInfo> getUserList(UserListRequest request);

    IPage<UserListInfo> pageUser(UserPageRequest request);

    UserDetail getUser(Integer dataId);

    boolean saveOrUpdateUser(UserDetail dto);

    boolean removeUser(Integer dataId);
}
