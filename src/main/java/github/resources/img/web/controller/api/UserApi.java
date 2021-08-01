package github.resources.img.web.controller.api;

import github.resources.img.check.core.Account;
import github.resources.img.check.core.AuthInfo;
import github.resources.img.check.core.WebSecurityManager;
import github.resources.img.check.core.exception.AuthException;
import github.resources.img.util.ResponseUtil;
import github.resources.img.web.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@Slf4j
public class UserApi {

    @Autowired
    private WebSecurityManager webSecurityManager;

    @PostMapping
    public Response login(@RequestBody Account account){
        try {
            AuthInfo authInfo = webSecurityManager.login(account);
            String token = webSecurityManager.generateToken(authInfo.getUserId());
            return ResponseUtil.ok(token);
        } catch (AuthException e) {
            log.info("failed to auth user,user=[{}]",account.getName());
        }
        return ResponseUtil.fail("failed to auth user");
    }



}
