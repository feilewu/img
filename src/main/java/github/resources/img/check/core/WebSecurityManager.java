package github.resources.img.check.core;

import github.resources.img.check.core.exception.AuthException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WebSecurityManager {

    AuthInfo login(Account account) throws AuthException;
    /**
     *
     * @param request
     * @param response
     * @return
     */
    boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String generateToken(String userId);
}
