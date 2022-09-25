package github.resources.img.manager;

import github.resources.img.manager.bo.ImageBo;
import github.resources.img.storage.Image;
import github.resources.img.storage.Storage;
import github.resources.img.storage.StorageFactory;
import github.resources.img.storage.StorageType;
import github.resources.img.storage.dao.ImageMetaMapper;
import github.resources.img.storage.dao.LocalImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:51
 **/
@Component("localImageManager")
public class LocalImageManager implements ImageManager, Storage {

    @Autowired
    private ImageMetaMapper imageMetaMapper;

    @Autowired
    private LocalImageMapper imageMapper;

    private final Storage storage;

    public LocalImageManager() {
        this.storage = StorageFactory.getInstance().getStorage(StorageType.LOCAL);
    }


    @Override
    public void write(Image image) {
        storage.write(image);
    }

    @Override
    public Image read(String imgName) {
        return storage.read(imgName);
    }

    @Override
    public void save(ImageBo imageBo) {

    }
}
