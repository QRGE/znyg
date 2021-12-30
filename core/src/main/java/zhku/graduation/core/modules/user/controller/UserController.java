package zhku.graduation.core.modules.user.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.core.modules.user.entity.bean.UserDetail;
import zhku.graduation.core.modules.user.entity.bean.UserListInfo;
import zhku.graduation.core.modules.user.entity.bean.UserListRequest;
import zhku.graduation.core.modules.user.entity.bean.UserPageRequest;
import zhku.graduation.core.modules.user.service.IUserService;
import zhku.graduation.basic.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.controller.BaseController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import static zhku.graduation.basic.constant.SystemState.ResponseState.*;

/**
 * @author QR
 * @since 2021-12-30
 */
@Api(tags = "用户表")
@RestController
@RequestMapping("/user/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "查询用户表详情", response = UserDetail.class)
    @GetMapping("get")
    public Result<?> getUser(@RequestParam Integer id){
        UserDetail info = userService.getUser(id);
        return Result.OK(info);
    }

    @ApiOperation(value = "查询用户表列表", response = UserListInfo.class)
    @PostMapping("list")
    public Result<?> getUserList(@RequestBody UserListRequest request){
        List<UserListInfo> list = userService.getUserList(request);
        return Result.OK(list);
    }

    @ApiOperation(value = "分页查询用户表列表", response = UserListInfo.class)
    @PostMapping("page")
    public Result<?> getUserList(@RequestBody UserPageRequest request){
        handlePageRequest(request);
        IPage<UserListInfo> page = userService.pageUser(request);
        return Result.OK(page);
    }

    @ApiOperation("新增或修改用户表")
    @PostMapping("edit")
    public Result<?> saveOrUpdateUser(@RequestBody UserDetail dto){
        boolean result = userService.saveOrUpdateUser(dto);
        return result ? Result.OK() : error(STATE_OPERATION_FAILURE);
    }

    @ApiOperation("删除用户表")
    @DeleteMapping("remove")
    public Result<?> removeUser(@RequestParam Integer id){
        boolean result = userService.removeUser(id);
        return result ? Result.OK() : error(STATE_OPERATION_FAILURE);
    }
}
