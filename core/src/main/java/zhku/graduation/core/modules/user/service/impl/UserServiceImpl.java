package zhku.graduation.core.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.core.modules.user.entity.bean.LoginUser;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;
import zhku.graduation.core.modules.user.entity.bean.UserListInfo;
import zhku.graduation.core.modules.user.entity.bean.UserPageRequest;
import zhku.graduation.core.modules.user.entity.po.User;
import zhku.graduation.core.modules.user.mapper.UserMapper;
import zhku.graduation.core.modules.user.service.IUserService;
import zhku.graduation.core.util.PasswordUtil;

import java.util.Optional;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-01-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean isValidUser(String account, String password) {
        LambdaQueryWrapper<User> queryWrapper1 = baseQueryWrapper();
        if (account.contains("@")) {
            queryWrapper1.eq(User::getEMail, account);
        }else {
            queryWrapper1.eq(User::getUsername, account);
        }
        User queryUser = getOne(queryWrapper1);
        if (queryUser == null) {
            return true;
        }
        return !queryUser.getPassword().equals(PasswordUtil.encrypt(account, password, queryUser.getSalt()));
    }

    @Override
    public boolean isValidUser(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username);
        User user = getOne(wrapper);
        return user != null;
    }

    @Override
    public LoginUser getUser(String username) {
        LambdaQueryWrapper<User> queryWrapper = baseQueryWrapper()
                .eq(User::getUsername, username);
        User user = getOne(queryWrapper);
        return Optional.ofNullable(user).map(u -> new LoginUser().parseFromPO(u)).orElse(null);
    }

    @Override
    public IPage<UserListInfo> pageUser(UserPageRequest request) {
        LambdaQueryWrapper<User> queryWrapper = baseQueryWrapper();
        IPage<User> page = new Page<>(request.getPage(), request.getPageSize());
        page = this.page(page, queryWrapper);
        return page.convert(UserListInfo::new);
    }

    @Override
    public UserDetail getUser(Integer dataId) {
        User user = getById(dataId);
        return Optional.ofNullable(user)
                        .map(po -> new UserDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public UserDetail getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username);
        User user = getOne(queryWrapper);
        return Optional.ofNullable(user).map(po -> new UserDetail().parseFromPo(po)).orElse(null);
    }

    @Override
    public boolean saveOrUpdateUser(UserDetail dto) {
        return saveOrUpdate(new User().parseFromDto(dto));
    }

    @Override
    public boolean removeUser(Integer dataId) {
        return removeById(dataId);
    }

    @Override
    public boolean addUser(String account, String password) {
        String salt = PasswordUtil.getSalt();
        password = PasswordUtil.encrypt(account, password, salt);
        return save(new User(account, password, salt));
    }

    @Override
    public boolean updatePwd(String newPwd, String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username);
        User user = getOne(wrapper);
        String newPassword = PasswordUtil.encrypt(username, newPwd, user.getSalt());
        user.setPassword(newPassword);
        return updateById(user);
    }

    private LambdaQueryWrapper<User> baseQueryWrapper() {
        return Wrappers.lambdaQuery(User.class)
                        .eq(User::getIsDel, 0);
    }

    private LambdaUpdateWrapper<User> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(User.class)
                        .eq(User::getIsDel, 0);
    }
}
