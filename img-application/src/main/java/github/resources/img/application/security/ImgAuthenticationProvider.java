package github.resources.img.application.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/27 22:44
 **/
@Component
public class ImgAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println(userName+"---"+password);
        return new UsernamePasswordAuthenticationToken(userName
                , password, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
