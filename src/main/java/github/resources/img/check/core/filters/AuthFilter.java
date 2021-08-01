package github.resources.img.check.core.filters;

import github.resources.img.check.core.WebSecurityManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Data
public class AuthFilter implements Filter {

    private WebSecurityManager webSecurityManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(webSecurityManager !=null && !webSecurityManager.authenticate((HttpServletRequest) request,(HttpServletResponse) response)){
            return;
        }
        chain.doFilter(request,response);
    }

}
