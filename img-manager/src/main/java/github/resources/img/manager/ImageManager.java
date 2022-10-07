package github.resources.img.manager;

import github.resources.img.core.Page;
import github.resources.img.core.model.bo.ImageBo;
import github.resources.img.core.model.vo.ImageVo;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:59
 **/
public interface ImageManager {

    void save(ImageBo imageBo);

    ImageBo get(String fileName);

    Page<ImageVo> getPage(long current,long size,String owner,String host);

}
