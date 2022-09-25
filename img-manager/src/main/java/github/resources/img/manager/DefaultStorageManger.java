package github.resources.img.manager;

import github.resources.img.manager.bo.ImageBo;
import github.resources.img.manager.dao.ImgMapper;
import github.resources.img.manager.storage.Storage;
import github.resources.img.manager.storage.StorageFactory;
import github.resources.img.manager.storage.StorageType;
import github.resources.img.manager.writer.LocalWriter;

import java.util.HashMap;
import java.util.Map;

public class DefaultStorageManger implements StorageManger{

    private final Map<StorageType, Storage> storageMap = new HashMap<>(10);

    public DefaultStorageManger(ImgMapper imgMapper){
        StorageFactory storageFactory = StorageFactory.getInstance();
        final Storage storage = storageFactory.getStorage(StorageType.LOCAL);
        final ImageWriter writer = storage.getWriter();
        if (writer instanceof LocalWriter) {
            LocalWriter localWriter = (LocalWriter) writer;
            localWriter.setImgMapper(imgMapper);
        }
        storageMap.put(StorageType.LOCAL, storage);
    }

    @Override
    public void write(ImageBo imageBo) {
        getDefaultStorage().getWriter().write(imageBo);
    }

    @Override
    public void write(ImageBo imageBo, StorageType... types) {

    }

    @Override
    public ImageBo read(String name, StorageType type) {
        final Storage storage = getStorage(type);
        return storage.getReader().read(name);
    }

    private Storage getDefaultStorage(){
        return getStorage(StorageType.LOCAL);
    }

    private Storage getStorage(StorageType type){
        Storage storage = storageMap.get(type);
        if (storage == null){
            throw new IllegalArgumentException("unsupported type: "+type);
        }
        return storage;
    }

}
