package github.resources.img.application.web.api;

import github.resources.img.application.dto.Response;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.auth.SecurityManager;
import github.resources.img.auth.subject.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Slf4j
public class UserApi {

    @Autowired
    private SecurityManager securityManager;

    @PostMapping("/")
    public Response login(@RequestParam("name") String name, @RequestParam("password") String password){
        Account account = Account.create(name,password);
        String token = securityManager.login(account);
        if(StringUtils.isNotBlank(token)){
            return ResponseUtil.ok(token);
        }else {
            return ResponseUtil.fail("failed to auth user");
        }

    }

}
