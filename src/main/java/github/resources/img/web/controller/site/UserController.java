package github.resources.img.web.controller.site;

import github.resources.img.check.core.Account;
import github.resources.img.check.core.WebSecurityManager;
import github.resources.img.check.core.exception.AuthException;
import github.resources.img.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WebSecurityManager webSecurityManager;

    @Autowired
    private UserService userService;

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam("name")String name, @RequestParam("password") String password,
                             HttpServletRequest request, HttpServletResponse response){
        try {
            webSecurityManager.login(new Account(name,password),request,response);
        } catch (AuthException e) {
            e.printStackTrace();
        }
        System.out.println("校验成功");
        return "redirect:/img/index.html";
    }

}
