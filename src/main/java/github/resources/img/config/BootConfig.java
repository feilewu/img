package github.resources.img.config;

import github.resources.img.check.core.*;
import github.resources.img.check.core.filters.AuthFilter;
import github.resources.img.check.core.token.DefaultTokenManager;
import github.resources.img.check.core.token.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.io.IOException;

@Configuration
public class BootConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(){
        // 新建过滤器注册类
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        // 添加自定义 过滤器
        registration.setFilter(authFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        //设置过滤器顺序
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public AuthFilter authFilter(){
        AuthFilter authFilter = new AuthFilter();
        authFilter.setWebSecurityManager(webSecurityManager());
        return authFilter;
    }

    @Bean
    public DefaultRuleEngine defaultRuleEngine(){
        DefaultRuleEngine defaultRuleEngine = new DefaultRuleEngine();
        defaultRuleEngine.addRule("/favicon.ico", RuleAction.PASS);
        defaultRuleEngine.addRule("/user/checkLogin",RuleAction.PASS);
        defaultRuleEngine.addRule("/user/login.html",RuleAction.PASS);
        defaultRuleEngine.addRule("/api/user",RuleAction.PASS);
        defaultRuleEngine.addRule("/**", RuleAction.INTERCEPT);
        defaultRuleEngine.addApiPattern("/api/**");
        return defaultRuleEngine;
    }

    @Bean
    public TokenManager tokenManager(){
        return new DefaultTokenManager();
    }

    @Bean
    public Realm realm(){
        return new JDBCRealm();
    }

    @Bean
    public WebSecurityManager webSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRuleEngine(defaultRuleEngine());
        defaultWebSecurityManager.setTokenManager(tokenManager());
        defaultWebSecurityManager.setRealm(realm());
        return defaultWebSecurityManager;
    }

    /**
     * 关闭tomcat的session管理
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sessionManagerCustomizer() {
        return server -> server.addContextCustomizers(context -> context.setManager(new SimpleSessionManager()));
    };

    @Slf4j
    static class SimpleSessionManager extends ManagerBase implements Lifecycle {

        @Override
        protected synchronized void startInternal() throws LifecycleException {
            super.startInternal();
            try {
                load();
            } catch (Throwable t) {
                ExceptionUtils.handleThrowable(t);
                t.printStackTrace();
            }
            setState(LifecycleState.STARTING);
        }

        @Override
        protected synchronized void stopInternal() throws LifecycleException {
            setState(LifecycleState.STOPPING);
            try {
                unload();
            } catch (Throwable t) {
                ExceptionUtils.handleThrowable(t);
                t.printStackTrace();
            }
            super.stopInternal();
        }

        @Override
        public void load(){}

        @Override
        public void unload(){}

        @Override
        public Session createSession(String sessionId) {
            return null;
        }

        @Override
        public Session createEmptySession() {
            return null;
        }

        @Override
        public void add(Session session) {}

        @Override
        public Session findSession(String id) throws IOException {
            return null;
        }

        @Override
        public Session[] findSessions(){
            return null;
        }

        @Override
        public void processExpires() {}
    }

}
