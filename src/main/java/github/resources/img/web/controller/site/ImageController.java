package github.resources.img.web.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("img")
public class ImageController {

    @GetMapping("/index.html")
    public String index(HttpServletRequest req, HttpServletResponse resp){
        return "index";
    }

}
