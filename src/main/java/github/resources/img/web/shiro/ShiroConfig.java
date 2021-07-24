//package github.resources.img.web.shiro;
//
//import org.apache.shiro.mgt.SessionsSecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
//import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
//import org.apache.shiro.session.mgt.eis.SessionDAO;
//import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
//import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
//import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
////@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public Realm realm() {
//        return new UserRealm();
//    }
//
////    @Bean
////    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Value("#{ @environment['shiro.loginUrl'] ?: '/login.jsp' }") String loginUrl,
////                                                         @Value("#{ @environment['shiro.successUrl'] ?: '/' }") String successUrl,
////                                                         @Value("#{ @environment['shiro.unauthorizedUrl'] ?: null }") String unauthorizedUrl,
////                                                         SessionsSecurityManager securityManager,
////                                                         ShiroFilterChainDefinition shiroFilterChainDefinition,
////                                                         Map<String, Filter> filterMap) {
////
////        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
////
////        filterFactoryBean.setLoginUrl(loginUrl);
////        filterFactoryBean.setSuccessUrl(successUrl);
////        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
////
////        filterFactoryBean.setSecurityManager(securityManager);
////        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
////        filterFactoryBean.setFilters(filterMap);
////
////        return filterFactoryBean;
////    }
//
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
//        definition.addPathDefinition("/favicon.ico", "anon");
//        definition.addPathDefinition("/user/checkLogin", "anon");
//        definition.addPathDefinition("/api/img/**", "anon");
//        definition.addPathDefinition("/**", "authc");
//        return definition;
//    }
//
//    /**
//     * 安全管理器
//     */
//    @Bean
//    public SessionsSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 自定义session管理
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setRealm(realm());
//        return securityManager;
//    }
//
//    /**
//     * 配置会话ID生成器
//     * @return
//     */
//    @Bean
//    public SessionIdGenerator sessionIdGenerator() {
//        return new JavaUuidSessionIdGenerator();
//    }
//
//    @Bean("sessionIdCookie")
//    public SimpleCookie sessionIdCookie(){
//        //这个参数是cookie的名称
//        SimpleCookie simpleCookie = new SimpleCookie("sid");
//        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
//
//        //setcookie()的第七个参数
//        //设为true后，只能通过http访问，javascript无法访问
//        //防止xss读取cookie
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setPath("/");
//        //maxAge=-1表示浏览器关闭时失效此Cookie
//        simpleCookie.setMaxAge(-1);
//        return simpleCookie;
//    }
//
//    @Bean
//    public SessionDAO sessionDAO() {
//        MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
//        memorySessionDAO.setSessionIdGenerator(sessionIdGenerator());
//        return memorySessionDAO;
//    }
//
//    /**
//     * 配置Session管理器
//     */
//    @Bean
//    public SessionManager sessionManager() {
//        DefaultWebSessionManager defaultWebSessionManager= new DefaultWebSessionManager();
//        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
//        defaultWebSessionManager.setSessionDAO(sessionDAO());
//        defaultWebSessionManager.setGlobalSessionTimeout(30*60*1000);
//        return defaultWebSessionManager;
//    }
//}
