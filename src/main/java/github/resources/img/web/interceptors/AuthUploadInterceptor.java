//package github.resources.img.web.interceptors;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.subject.support.DefaultSubjectContext;
//import org.apache.shiro.web.session.mgt.WebSessionKey;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.Serializable;
//
///**
// * 控制图片查看
// */
//@Component
//public class AuthUploadInterceptor implements HandlerInterceptor {
//
////    @Override
////    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        boolean status = false;
////        Serializable id = SecurityUtils.getSubject().getSession().getId();
////        WebSessionKey key = new WebSessionKey(id, request, response);
////        Session se = SecurityUtils.getSecurityManager().getSession(key);
////        Object obj = se.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
////        if(obj != null){
////            status = (Boolean) obj;
////        }
////        System.out.println(status);
////        return HandlerInterceptor.super.preHandle(request, response, handler);
////    }
//}
