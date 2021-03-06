package zhku.graduation.core.modules.user.controller;


import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.user.entity.bean.*;
import zhku.graduation.core.modules.user.service.IUserService;
import zhku.graduation.core.util.JwtUtil;
import zhku.graduation.core.util.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static zhku.graduation.basic.constant.HttpStatus.ERROR;

/**
 * @author QR
 * @since 2022-01-10
 */
@Slf4j
@Api(tags = "用户表")
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {


    @Lazy
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private IUserService userService;

    @ApiOperation("更新用户密码")
    @PostMapping("updatePwd")
    public Result<?> updatePwd(@Valid @RequestBody UpdatePwdBean request){
        String email = request.getEmail();
        if (!Validator.isEmail(email)
            || !userService.isUserEmail(request.getUsername(), request.getEmail())) {
            return Result.error("请输入该用户配对的邮箱");
        }
        String realRequest = (String) redisUtil.get(email);
        if (StrUtil.isBlank(realRequest) || !realRequest.equals(request.getCaptcha())) {
            return Result.error("验证码无效");
        }
        boolean result = userService.updatePwd(request.getNewPwd(), request.getUsername());
        return result ? Result.OK("修改密码成功！") : Result.error("修改密码失败！");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result<?> getUserInfo(){
        JSONObject result = new JSONObject();
        result.set("name", "QR");
        result.set("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.OK(result);
    }

    @ApiOperation("用户登陆")
    @PostMapping("login")
    public Result<?> login(
            @Valid @RequestBody LoginUser loginInfo
    ) {
        String account = loginInfo.getAccount();
        String password = loginInfo.getPassword();
        if (userService.isValidUser(account, password)) {
            log.error("用户：{} 登陆失败", account);
            return Result.error("登陆校验失败，请重新登陆");
        }
        UserDetail userDetail = userService.getUserByUsername(account);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("userInfo", userDetail);
        String token = JwtUtil.sign(account, password);
        // token:token , 可以实现 token 刷新
        redisUtil.set(token, token);
        redisUtil.expire(token, JwtUtil.EXPIRE_TIME);
        jsonObject.set("token", token);
        log.info("账户: {} 登陆, 生成token: {}", account, token);
        return Result.OK("登陆成功", jsonObject);
    }

    @ApiOperation("用户退出")
    @PostMapping("logout")
    public Result<?> logout(HttpServletRequest request){
        String token = request.getHeader(Constant.TOKEN);
        // 清除 token
        if (StrUtil.isNotBlank(token)) {
            redisUtil.del(token);
        }
        log.info("清除token: {}", token);
        return Result.OK("退出成功");
    }

    @ApiOperation(value = "查询用户表详情", response = UserDetail.class)
    @GetMapping("get")
    public Result<?> getUser(@RequestParam Integer id){
        UserDetail info = userService.getUser(id);
        return Result.OK(info);
    }

    @ApiOperation("新增用户")
    @PostMapping("add")
    public Result<?> addUser(
            @Valid @RequestBody LoginUser user
    ){
        String account = user.getAccount();
        String password = user.getPassword();
        boolean result = userService.addUser(account, password);
        return result ? Result.OK() : error(ERROR);
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
        return result ? Result.OK() : error(ERROR);
    }

    @ApiOperation("删除用户表")
    @DeleteMapping("remove")
    public Result<?> removeUser(@RequestParam Integer id){
        boolean result = userService.removeUser(id);
        return result ? Result.OK() : error(ERROR);
    }
}
