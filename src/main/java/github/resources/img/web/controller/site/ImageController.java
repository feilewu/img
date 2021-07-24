package github.resources.img.web.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("img")
public class ImageController {

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }

}
