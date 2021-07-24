package github.resources.img.web.controller.api;

import github.resources.img.web.dto.Response;
import github.resources.img.web.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/img")
public class ImageApi {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Response upload(@RequestPart MultipartFile multipartFile){
        return imageService.saveImage(multipartFile);
    }

}
