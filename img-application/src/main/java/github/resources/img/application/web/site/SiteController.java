package github.resources.img.application.web.site;

import github.resources.img.application.configuration.ContextHolder;
import github.resources.img.application.configuration.ImageServiceHolder;
import github.resources.img.application.configuration.UserContext;
import github.resources.img.application.model.dto.Response;
import github.resources.img.application.service.GuestService;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.BeanConventUtil;
import github.resources.img.application.utils.ImageUtil;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.manager.bo.ImageBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("site")
public class SiteController {

    @Autowired
    private ImgService imgService;

    @Autowired
    private GuestService guestService;

    @GetMapping("login.html")
    public String login(){
        return "login";
    }

    @ResponseBody
    @PostMapping("/img/upload")
    public Response upload(@RequestPart MultipartFile file, HttpServletRequest request){
        if (!ImageUtil.supportedImageType(file, ImageServiceHolder.getImageServiceConf())){
            return ResponseUtil.fail("unsupported image type");
        }
        ImageBo imageBo = BeanConventUtil.toBo(file);
        final Response response = imgService.upload(imageBo);
        if (response.isSuccess()){
            final UserContext context = ContextHolder.getInstance().getContext();
            if (context.isGuest()){
                String ip = context.getIp();
                guestService.updateGuestUpload(ip);
            }
        }
        return response;
    }

}
