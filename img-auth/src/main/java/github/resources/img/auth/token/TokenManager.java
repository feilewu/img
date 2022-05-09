package github.resources.img.auth.token;

import github.resources.img.auth.AuthException;

public interface TokenManager {


    Token checkToken(String token) throws AuthException;

    String generateToken(String principal);

    String generateToken(String principal, Long expireTime);


}
