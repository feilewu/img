package github.resources.img.web.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class RootSiteController {

    @GetMapping("favicon.ico")
    @ResponseBody
    public void favicon(){

    }

    @GetMapping("index.html")
    public String index(HttpServletRequest req, HttpServletResponse resp){
        return "index";
    }

}
