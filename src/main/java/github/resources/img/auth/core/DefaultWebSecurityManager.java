package github.resources.img.auth.core;

import github.resources.img.auth.core.check.AuthInfo;
import github.resources.img.auth.core.check.Realm;
import github.resources.img.auth.core.exception.AuthException;
import lombok.Data;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
public class DefaultWebSecurityManager implements WebSecurityManager{

    private DefaultRuleEngine defaultRuleEngine;

    private Realm realm;

    public DefaultWebSecurityManager(){
        defaultRuleEngine = new DefaultRuleEngine();
    }

    @Override
    public void login(Account account, HttpServletRequest request, HttpServletResponse response) throws AuthException {
        //校验通过
        AuthInfo authInfo = realm.auth(account);
        if(authInfo!=null){
            Cookie cookie = new Cookie("token",authInfo.getUserName()+"_"+defaultRuleEngine.createToken());
            cookie.setMaxAge(30 * 60);// 设置为30min
            cookie.setPath("/");
            response.addCookie(cookie);
        }else {
            throw new AuthException("error name or password");
        }

    }

    @Override
    public boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(RuleAction.PASS.equals(defaultRuleEngine.match(request))){
            return true;
        }else {
            if(checkToken(request)){
                return true;
            }
            response.sendRedirect("/user/login.html");
            return false;
        }
    }

    protected boolean checkToken(HttpServletRequest request){
        return false;
    }

}
