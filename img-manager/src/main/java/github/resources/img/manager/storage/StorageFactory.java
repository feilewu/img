package github.resources.img.manager.storage;

import github.resources.img.manager.exception.WriteRunTimeException;

public class StorageFactory {

    private StorageFactory(){

    }

    private static final class InstanceHolder {
        static final StorageFactory instance = new StorageFactory();
    }

    public static StorageFactory getInstance(){
        return InstanceHolder.instance;
    }

    public Storage getStorage(StorageType type){
        switch (type){
            case LOCAL:
                return new LocalStorage();
            case OSS:
            case COS:
            default:
                throw new WriteRunTimeException("unsupported storageType: "+type);
        }

    }

}
