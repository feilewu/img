package github.resources.img.auth.core;

import github.resources.img.auth.core.check.AuthInfo;
import github.resources.img.auth.core.check.Realm;
import github.resources.img.auth.core.check.TokenManager;
import github.resources.img.auth.core.exception.AuthException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Slf4j
public class DefaultWebSecurityManager implements WebSecurityManager{

    private DefaultRuleEngine defaultRuleEngine;

    private Realm realm;

    private TokenManager tokenManager;

    public DefaultWebSecurityManager(){
    }

    @Override
    public void login(Account account, HttpServletRequest request, HttpServletResponse response) throws AuthException {
        //校验通过
        AuthInfo authInfo = realm.auth(account);
        if(authInfo!=null){
            Cookie cookie = new Cookie("token",tokenManager.generateToken(authInfo.getUserId()));
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
        String token = getTokenFromHeader(request);
        if(!StringUtils.hasText(token)){
            token = getTokenFromCookie(request);
        }
        try {
            tokenManager.checkToken(token);
        } catch (AuthException e) {
            log.info("token=[{}]Unverified",token);
            return false;
        }
        return true;
    }

    protected String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    protected String getTokenFromHeader(HttpServletRequest request){
        return request.getHeader("token");
    }


}
