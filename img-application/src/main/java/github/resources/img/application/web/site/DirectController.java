package github.resources.img.application.web.site;

import github.resources.img.auth.AuthException;
import github.resources.img.auth.SecurityManager;
import github.resources.img.auth.subject.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("direct")
@Slf4j
public class DirectController {

    @Autowired
    private SecurityManager securityManager;

    @PostMapping("checkLogin")
    public String checkLogin(@RequestParam("name")String name, @RequestParam("password") String password,
                             HttpServletRequest request, HttpServletResponse response){
        Account account = Account.create(name,password);
        final String token = securityManager.login(account);
        if(ObjectUtils.isEmpty(token)){
            return "redirect:/site/login.html";
        }
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
        log.info("user:[{}] login successfully",name);
        return "redirect:/user";
    }
}
