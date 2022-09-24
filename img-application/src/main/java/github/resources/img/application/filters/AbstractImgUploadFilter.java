package github.resources.img.application.filters;

import github.resources.img.application.config.ContextHolder;
import github.resources.img.application.config.ImageServiceConf;
import github.resources.img.application.config.ImageServiceHolder;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.application.utils.WebUtil;
import github.resources.img.auth.SecurityManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static github.resources.img.application.config.ImageServiceConf.ENABLE_VERIFICATION;

public abstract class AbstractImgUploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContextHolder.getInstance().getContext().clear();
        ImageServiceConf conf = ImageServiceHolder.getImageServiceConf();
        if (!conf.getBoolean(ENABLE_VERIFICATION.key(), ENABLE_VERIFICATION.defaultValue())){
            chain.doFilter(request,response);
            return;
        }
        String tokenStr = WebUtil.getToken((HttpServletRequest) request);
        final SecurityManager securityManager = ImageServiceHolder.getSecurityManager();
        if (securityManager.authenticate(tokenStr)){
            chain.doFilter(request,response);
            return;
        }
        if (doCustomAuth((HttpServletRequest) request, (HttpServletResponse)response)){
            chain.doFilter(request,response);
            return;
        }
        WebUtil.replyJson((HttpServletResponse) response, ResponseUtil.fail("failed to auth user"));
    }
    protected boolean doCustomAuth(HttpServletRequest request, HttpServletResponse response){
        return false;
    }
}
