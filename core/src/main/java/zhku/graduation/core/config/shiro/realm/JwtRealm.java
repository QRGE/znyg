package zhku.graduation.core.config.shiro.realm;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import zhku.graduation.basic.exception.InValidTokenException;
import zhku.graduation.core.config.shiro.JwtToken;
import zhku.graduation.core.util.JwtUtil;

/**
 * @author qr
 * @date 2022/1/11 22:55
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    //这个 token 就是从过滤器中传入的 jwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new InValidTokenException("jwtToken 不允许为空");
        }
        //判断
        JwtUtil jwtUtil = new JwtUtil();
        if (jwtUtil.isNotVerify(jwt)) {
            throw new UnknownAccountException();
        }
        //下面是验证这个user是否是真实存在的
        String username = (String) jwtUtil.decode(jwt).get("username");//判断数据库中username是否存在
        log.info("在使用token登录"+username);
        return new SimpleAuthenticationInfo(jwt,jwt,"JwtRealm");
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名

    }

}

