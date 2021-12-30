package zhku.graduation.core.modules.user.service.impl;

import zhku.graduation.core.modules.user.entity.po.User;
import zhku.graduation.core.modules.user.mapper.UserMapper;
import zhku.graduation.core.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;
import zhku.graduation.core.modules.user.entity.bean.UserListInfo;
import zhku.graduation.core.modules.user.entity.bean.UserListRequest;
import zhku.graduation.core.modules.user.entity.bean.UserPageRequest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author QR
 * @since 2021-12-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<UserListInfo> getUserList(UserListRequest request) {
        LambdaQueryWrapper<User> queryWrapper = baseQueryWrapper();
        List<User> poList = list(queryWrapper);
        List<UserListInfo> dtoList = new ArrayList<>(poList.size());
        if (!poList.isEmpty()) {
            dtoList = poList.stream().map(UserListInfo::new).collect(Collectors.toList());
        }
        return dtoList;
    }

    @Override
    public IPage<UserListInfo> pageUser(UserPageRequest request) {
        LambdaQueryWrapper<User> queryWrapper = baseQueryWrapper();
        IPage<User> page = new Page<>(request.getPageNum(), request.getPageSize());
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
    public boolean saveOrUpdateUser(UserDetail dto) {
        User user = new User();
        if(dto.getId() == null){
            user = user.init();
        }
        user.parseFromDto(dto);
        return saveOrUpdate(user);
    }

    @Override
    public boolean removeUser(Integer dataId) {
        return removeById(dataId);
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