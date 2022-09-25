package github.resources.img.storage;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:40
 **/
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
                throw new RuntimeException("unsupported storageType: "+type);
        }

    }

}
