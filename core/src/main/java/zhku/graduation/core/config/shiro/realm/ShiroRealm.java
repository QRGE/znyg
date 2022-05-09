package zhku.graduation.core.config.shiro.realm;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import zhku.graduation.core.config.shiro.JwtToken;
import zhku.graduation.core.modules.user.entity.bean.LoginUser;
import zhku.graduation.core.modules.user.service.IUserService;
import zhku.graduation.core.util.JwtUtil;
import zhku.graduation.core.util.RedisUtil;

import javax.annotation.Resource;
import java.net.InetAddress;

@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Lazy
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private IUserService userService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限信息认证: 用户访问 controller 的时候才进行验证(redis存储的此处权限信息)
     *
     * @param principals 身份信息
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = null;
        if (principals != null) {
            LoginUser sysUser = (LoginUser) principals.getPrimaryPrincipal();
            username = sysUser.getAccount();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO 权限认证再做判断, 主要判断用户是不是 admin
        log.info("======================用户：{} 进行Shiro权限认证======================", username);
        return info;
    }

    /**
     * 用户进行登录的时候进行验证(不存redis)
     * 也就是说验证用户输入的账号和密码是否正确，错误抛出异常
     * @param auth 用户登录的账号密码信息
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        log.debug("============用户登陆身份认证开始============");
        String token = (String) auth.getCredentials();
        if (token == null) {
            String ip = InetAddress.getLocalHost().getHostAddress();
            log.info("————————身份认证失败——————————IP地址: {} ", ip);
            throw new AuthenticationException("token为空!");
        }
        // 校验token有效性
        LoginUser loginUser = this.checkUserTokenIsEffect(token);
        return new SimpleAuthenticationInfo(loginUser, token, getName());
    }

    /**
     * 校验token的有效性
     */
    public LoginUser checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token 无效, 校验失败!");
        }
        // 查询用户信息
        LoginUser loginUser = userService.getUser(username);
        if (loginUser == null) {
            throw new AuthenticationException("用户不存在!");
        }
        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, username, loginUser.getPassword())) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        return loginUser;
    }

    /**
     * JWT 刷新
     * 1、登录成功后将用户生成的Token作为 k、v 存储到缓存里面(这时候 k、v 值一样, k:v token:token)，缓存有效期设置为Jwt有效时间的2倍
     * 2、用户再次请求时，通过JWTFilter层层校验之后会进入到 doGetAuthenticationInfo 进行身份验证
     * 3、用户请求的 jwt 已超时，但该 token 对应 cache 中的k还是存在，则表示该用户一直在操作只是 token 失效了，
     *    程序会给 token 对应的 k 映射的 v 值重新生成 JWT 并覆盖v值，该缓存生命周期重新计算
     * 4、用户请求 jwt 经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     */
    private boolean jwtTokenRefresh(String token, String userName, String passWord) {
        String cacheToken = String.valueOf(redisUtil.get(token));
        if (StrUtil.isNotBlank(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verify(cacheToken, userName, passWord)) {
                String newAuthorization = JwtUtil.sign(userName, passWord);
                // 设置超时时间
                redisUtil.set(token, newAuthorization);
                redisUtil.expire(token, JwtUtil.EXPIRE_TIME * 2 / 1000);
                log.info("==========用户: {}在线操作，更新token保证不掉线==========", userName);
            }
            return true;
        }
        return false;
    }

    /**
     * 清除当前用户的权限认证缓存
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

}
