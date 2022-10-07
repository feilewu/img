package github.resources.img.application.security;

import github.resources.img.application.security.token.TokenManager;
import github.resources.img.core.model.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/27 22:44
 **/
@Component
public class ImgAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final Object principal = authentication.getPrincipal();
        final Response response = tokenManager.checkToken((String) principal);
        if(response.isSuccess()){
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(response.getObj(), null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return usernamePasswordAuthenticationToken;
        }else {
            throw new BadCredentialsException(response.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
