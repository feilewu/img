package github.resources.img.application.service.impl;

import github.resources.img.application.configuration.ImageServiceHolder;
import github.resources.img.core.model.dto.Response;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.manager.ImageManager;
import github.resources.img.core.model.bo.ImageBo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static github.resources.img.application.configuration.DefaultImageServiceConf.HOST;

@Service
public class ImgServiceImpl implements ImgService {

    @Resource(name = "localImageManager")
    private ImageManager imageManager;

    @Override
    public Response saveImage(ImageBo imageBo) {
        imageManager.save(imageBo);
        String host = ImageServiceHolder.getImageServiceConf().getString(HOST);
        return ResponseUtil.ok(host+"/img/"+imageBo.getName()+"."+imageBo.getSuffix());
    }

    @Override
    public ImageBo getImg(String fileName) {
        return imageManager.get(fileName);
    }

}
