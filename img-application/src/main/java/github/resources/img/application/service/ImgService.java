package github.resources.img.application.service;

import github.resources.img.core.model.dto.Response;
import github.resources.img.core.model.bo.ImageBo;

public interface ImgService {

    Response saveImage(ImageBo imageBo);

    ImageBo getImg(String fileName);

}
