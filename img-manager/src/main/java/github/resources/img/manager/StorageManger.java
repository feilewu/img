package github.resources.img.manager;

import github.resources.img.manager.bo.ImageBo;
import github.resources.img.manager.storage.StorageType;

public interface StorageManger {

    void write(ImageBo imageBo);

    void write(ImageBo imageBo, StorageType ...types);

    /**
     * 图片的全路径名
     * @param name
     * @param type
     * @return
     */
    ImageBo read(String name, StorageType type);

}
