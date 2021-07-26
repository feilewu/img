package github.resources.img.auth.core;

import github.resources.img.auth.core.exception.AuthException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WebSecurityManager {

    void login(Account account, HttpServletRequest request,HttpServletResponse response) throws AuthException;
    /**
     *
     * @param request
     * @param response
     * @return
     */
    boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
