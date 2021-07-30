package github.resources.img.check.core;

import github.resources.img.check.core.exception.AuthException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    final class MutableHttpServletRequest extends HttpServletRequestWrapper {
        // holds custom header and value mapping
        private final Map<String, String> customHeaders;

        public MutableHttpServletRequest(HttpServletRequest request){
            super(request);
            this.customHeaders = new HashMap<String, String>();
        }

        public void putHeader(String name, String value){
            this.customHeaders.put(name, value);
        }

        public String getHeader(String name) {
            // check the custom headers first
            String headerValue = customHeaders.get(name);

            if (headerValue != null){
                return headerValue;
            }
            // else return from into the original wrapped object
            return ((HttpServletRequest) getRequest()).getHeader(name);
        }

        public Enumeration<String> getHeaderNames() {
            // create a set of the custom header names
            Set<String> set = new HashSet<String>(customHeaders.keySet());

            // now add the headers from the wrapped request object
            @SuppressWarnings("unchecked")
            Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
            while (e.hasMoreElements()) {
                // add the names of the request headers into the list
                String n = e.nextElement();
                set.add(n);
            }

            // create an enumeration from the set and return
            return Collections.enumeration(set);
        }
    }


}
