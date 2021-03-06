package zhku.graduation.core.config.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zhku.graduation.core.config.shiro.filter.JwtFilter;
import zhku.graduation.core.config.shiro.realm.ShiroRealm;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*
     * 不创建 Session
     * */
    @Bean
    public SubjectFactory subjectFactory() {
        return new JwtDefaultSubjectFactory();
    }

    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        // 关闭 ShiroDAO 功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        //禁止Subject的getSession方法
        securityManager.setSubjectFactory(subjectFactory());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        Map<String, Filter> filters = new HashMap<>();
        // 注册 jwt 拦截器, 即自定义拦截规则
        filters.put("jwt", new JwtFilter());
        shiroFilter.setFilters(filters);
        // 拦截器
        Map<String, String> filterUrls = new LinkedHashMap<>();
        // 先只放行登陆和推出
        filterUrls.put("/user/login", "anon");
        filterUrls.put("/user/logout", "anon");
        filterUrls.put("/tool/**", "anon"); // 工具类, 例如发送邮箱验证码
        filterUrls.put("/user/updatePwd", "anon"); // 修改密码
        filterUrls.put("/node/**", "anon"); // 鱼缸节点
        filterUrls.put("/quartz/**","anon"); // 定时任务
        filterUrls.put("/limit/**", "anon"); // 发送命令
        filterUrls.put("/command/**", "anon"); // 控制命令
        filterUrls.put("/record/**", "anon"); // 历史记录
        // 所有的接口都要走 jwt 拦截规则
        filterUrls.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(filterUrls);
        return shiroFilter;
    }
}


