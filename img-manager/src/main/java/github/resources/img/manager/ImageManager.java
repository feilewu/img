package github.resources.img.manager;

import github.resources.img.core.model.bo.ImageBo;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:59
 **/
public interface ImageManager {

    void save(ImageBo imageBo);

    ImageBo get(String fileName);

}
