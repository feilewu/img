package github.resources.img.web.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("site")
public class SiteController {

    @GetMapping("login.html")
    public String login(){
        return "login";
    }


}