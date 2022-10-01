package github.resources.img.application.security;

import github.resources.img.application.security.token.TokenManager;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.application.utils.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
 * @date 2022/9/28 21:41
 **/
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token= getAuthorization(request);
        try {
            String id = tokenManager.checkToken(token);
            // 构建AuthenticationToken
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(id, null, null);
            // 把AuthenticationToken放到当前线程,表示认证完成
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (Exception e){
            //WebUtil.replyJson(response, ResponseUtil.fail(e.getMessage()));
        }
        filterChain.doFilter(request, response);
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
