package github.resources.img.application.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.resources.img.application.model.dto.Response;
import github.resources.img.application.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/27 21:37
 **/
@Component
public class ImgAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final Response reply = ResponseUtil.fail("您没有认证或者没有权限");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(reply));
    }
}
