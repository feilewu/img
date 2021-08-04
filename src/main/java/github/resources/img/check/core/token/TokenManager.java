package github.resources.img.check.core.token;

import github.resources.img.check.core.exception.AuthException;

public interface TokenManager {


    Token checkToken(String token) throws AuthException;

    String generateToken(String userId);

    String generateToken(String userId, Long expireTime);


}
