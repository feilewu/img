package github.resources.img.application.web.api;

import github.resources.img.application.configuration.DefaultImageServiceConf;
import github.resources.img.application.model.dto.Response;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.BeanConventUtil;
import github.resources.img.application.utils.ImageUtil;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.manager.bo.ImageBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/img")
public class ImgApi {

    @Autowired
    private ImgService imgService;

    @Autowired
    private DefaultImageServiceConf conf;

    @PostMapping("/upload")
    public Response upload(@RequestPart MultipartFile file){
        if (!ImageUtil.supportedImageType(file,conf)){
            return ResponseUtil.fail("unsupported image type");
        }
        ImageBo imageBo = BeanConventUtil.toBo(file);
        return imgService.upload(imageBo);
    }

    @PostMapping("")
    public Response postImage(@RequestPart MultipartFile file) {
        if (!ImageUtil.supportedImageType(file,conf)){
            return ResponseUtil.fail("unsupported image type");
        }
        ImageBo imageBo = BeanConventUtil.toBo(file);
        return imgService.saveImage(imageBo);
    }




}


