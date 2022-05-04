package github.resources.img.auth;

import github.resources.img.auth.realm.Realm;
import github.resources.img.auth.subject.Subject;
import github.resources.img.auth.token.TokenManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SimpleSecurityManager implements SecurityManager{

    private Realm realm;

    private TokenManager tokenManager;

    @Override
    public String login(Subject subject) {
        try {
            realm.auth(subject) ;
        }catch (AuthException authException){
            log.info("{} auth failed",subject.getPrincipal().string());
            return null;
        }
        return tokenManager.generateToken(subject.getPrincipal().string());
    }

    @Override
    public boolean authenticate(String token) {
        try {
            tokenManager.checkToken(token);
        } catch (AuthException e) {
            return false;
        }
        return true;
    }
}
