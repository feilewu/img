package github.resources.img.web.controller.site;

import github.resources.img.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam("name")String name,@RequestParam("password") String password){
//        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(name+password);
        return "redirect:/img/index.html";
    }

}
