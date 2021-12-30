package zhku.graduation.core.modules.user.service;

import zhku.graduation.core.modules.user.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;
import zhku.graduation.core.modules.user.entity.bean.UserListInfo;
import zhku.graduation.core.modules.user.entity.bean.UserListRequest;
import zhku.graduation.core.modules.user.entity.bean.UserPageRequest;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 用户表服务类
 * </p>
 *
 * @author QR
 * @since 2021-12-30
 */
public interface IUserService extends IService<User> {

    List<UserListInfo> getUserList(UserListRequest request);

    IPage<UserListInfo> pageUser(UserPageRequest request);

    UserDetail getUser(Integer dataId);

    boolean saveOrUpdateUser(UserDetail dto);

    boolean removeUser(Integer dataId);
}
