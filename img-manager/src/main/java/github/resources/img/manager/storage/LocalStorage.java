package github.resources.img.manager.storage;

import github.resources.img.manager.reader.LocalReader;
import github.resources.img.manager.writer.LocalWriter;

public class LocalStorage extends AbstractStorage{

    public LocalStorage (){
        setImageWriter(new LocalWriter());
        setImageReader(new LocalReader());
    }


}
