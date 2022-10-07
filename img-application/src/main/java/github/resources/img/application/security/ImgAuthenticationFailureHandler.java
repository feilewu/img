package github.resources.img.application.security;

import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.application.utils.WebUtil;
import github.resources.img.core.ResponseStatus;
import github.resources.img.core.model.dto.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/6 21:01
 **/
@Component
public class ImgAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final Response result = ResponseUtil.fail(String.valueOf(ResponseStatus.TOKEN_ILLEGAL.getCode()), exception.getMessage());
        WebUtil.replyJson(response,result);
    }
}
