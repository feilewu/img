package github.resources.img.web.interceptors;

import github.resources.img.check.core.ContextHolder;
import github.resources.img.check.core.UserContext;
import github.resources.img.check.core.token.Token;
import github.resources.img.check.core.token.TokenManager;
import github.resources.img.check.core.utils.WebUtil;
import github.resources.img.web.exception.UserFriendlyRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UploadInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserContext context = ContextHolder.getInstance().getContext();
        if(StringUtils.isNotEmpty(context.getUserId())){
            throw new UserFriendlyRuntimeException("api/img/upload 进入拦截器前上下文userId不为空");
        }
        String tokenStr = WebUtil.getToken(request);
        try {
            Token token = tokenManager.checkToken(tokenStr);
            context.setUserId(token.getUserId());
        }catch (Exception e){
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
