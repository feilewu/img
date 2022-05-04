package github.resources.img.application.service;

import github.resources.img.application.dto.Response;
import github.resources.img.manager.bo.ImageBo;

public interface ImgService {

    Response upload(ImageBo imageBo);

    ImageBo readImg(String fileName);

}
