package github.resources.img.application.web.api;

import github.resources.img.application.security.token.TokenManager;
import github.resources.img.application.service.UserService;
import github.resources.img.application.utils.CommonUtil;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.core.model.bo.UserBo;
import github.resources.img.core.model.dto.Response;
import github.resources.img.core.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Slf4j
public class UserApi {


    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public Response login(@RequestBody UserEntity userEntity){
        userService.authUser(userEntity.getName(),userEntity.getPassword());
        return ResponseUtil.ok(tokenManager.generateToken(userEntity.getName()));
    }

    @PostMapping ("/register")
    public Response register(@RequestBody UserBo userBo){
        if (CommonUtil.isEmpty(userBo.getName())){
            return ResponseUtil.fail("用户名不能为空");
        }
        if (CommonUtil.isEmpty(userBo.getPassword())){
            return ResponseUtil.fail("密码不能为空");
        }
        if ( userService.addUser(userBo)){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail("添加用户失败，发生不可知错误！");
    }

    @GetMapping("/refreshToken")
    public Response refreshToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String token = tokenManager.generateToken((String) authentication.getPrincipal());
        return ResponseUtil.ok(token);
    }

}
