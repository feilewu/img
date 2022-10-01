package github.resources.img.application.web.site;

import github.resources.img.application.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("site")
public class SiteController {

    @Autowired
    private ImgService imgService;

    @GetMapping("login.html")
    public String login(){
        return "login";
    }

}
