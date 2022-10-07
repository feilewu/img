package github.resources.img.application.service.impl;

import github.resources.img.application.configuration.ContextHolder;
import github.resources.img.application.configuration.ImageServiceHolder;
import github.resources.img.core.Page;
import github.resources.img.core.model.dto.Response;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.core.model.vo.ImageVo;
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

    @Override
    public Response selectPage(long current, long size) {
        final String userId = ContextHolder.getInstance().getContext().getUserId();
        String host = ImageServiceHolder.getImageServiceConf().getString(HOST);
        final Page<ImageVo> page = imageManager.getPage(current, size, userId, host);
        return ResponseUtil.ok(page);
    }

}
