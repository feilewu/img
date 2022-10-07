package github.resources.img.application.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/6 20:42
 **/
public class AuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public AuthenticationToken(String token,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
    }


    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
