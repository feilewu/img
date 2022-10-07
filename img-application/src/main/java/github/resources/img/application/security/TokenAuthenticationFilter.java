package github.resources.img.application.security;

import github.resources.img.application.configuration.ContextHolder;
import github.resources.img.application.configuration.UserContext;
import github.resources.img.application.security.token.TokenManager;
import github.resources.img.application.utils.WebUtil;
import github.resources.img.core.model.dto.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/7 13:08
 **/
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManager tokenManager;

    private final TokenFilterRequestMatcher tokenFilterRequestMatcher = new TokenFilterRequestMatcher("/**");

    public TokenAuthenticationFilter() {
        tokenFilterRequestMatcher.setPass("/api/user/login","/img/**");
    }

    @Override
    protected boolean shouldNotFilter(@NotNull HttpServletRequest request) throws ServletException {
        return !tokenFilterRequestMatcher.matches(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token= getAuthorization(request);
        final Response checkTokenResp = tokenManager.checkToken(token);
        UserContext userContext = ContextHolder.getInstance().getContext();
        userContext.clear();
        if(checkTokenResp.isSuccess()){
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(checkTokenResp.getObj(), null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            userContext.setUserId((String) checkTokenResp.getObj());
            filterChain.doFilter(request,response);
        }else {
            WebUtil.replyJson(response,checkTokenResp);
        }

    }

    public String getAuthorization(HttpServletRequest request) {
        return request.getHeader("token");
    }

}
