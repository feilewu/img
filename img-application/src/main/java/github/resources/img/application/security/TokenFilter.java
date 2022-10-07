package github.resources.img.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/28 21:41
 **/
@Component
public class TokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final TokenFilterRequestMatcher DEFAULT_PATH_REQUEST_MATCHER = new TokenFilterRequestMatcher("/**");

    protected TokenFilter() {
        super(DEFAULT_PATH_REQUEST_MATCHER);
        DEFAULT_PATH_REQUEST_MATCHER.setPass("/api/user/login","/img/**");
        setContinueChainBeforeSuccessfulAuthentication(true);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token= getAuthorization(request);
        final AuthenticationToken authenticationToken = new AuthenticationToken(token, null);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    @Autowired
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Override
    @Autowired
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }


    /**
     * 从 request 的 header 中获取 Authorization
     *
     * @param request 请求
     * @return JWT
     */
    public String getAuthorization(HttpServletRequest request) {
        return request.getHeader("token");
    }


}
