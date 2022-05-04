package github.resources.img.application.appconfig;

import github.resources.img.auth.SecurityManager;
import github.resources.img.auth.SimpleSecurityManager;
import github.resources.img.auth.realm.Realm;
import github.resources.img.auth.token.DefaultTokenManager;
import github.resources.img.auth.token.TokenManager;
import github.resources.img.manager.DefaultStorageManger;
import github.resources.img.manager.StorageManger;
import github.resources.img.manager.dao.ImgMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class BootConfig {

    @Autowired
    private ImgMapper imgMapper;

    /**
     * 关闭tomcat的session管理
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sessionManagerCustomizer() {
        return server -> server.addContextCustomizers(context -> context.setManager(new SimpleSessionManager()));
    }

    @Bean
    public TokenManager tokenManager(){
        return new DefaultTokenManager();
    }

    @Bean
    public SecurityManager securityManager(Realm realm){
        SimpleSecurityManager securityManager = new SimpleSecurityManager();
        securityManager.setTokenManager(tokenManager());
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public StorageManger storageManger(){
        return new DefaultStorageManger(imgMapper);
    }

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
