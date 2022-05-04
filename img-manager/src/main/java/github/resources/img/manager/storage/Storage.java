package github.resources.img.manager.storage;

import github.resources.img.manager.ImageReader;
import github.resources.img.manager.ImageWriter;

public interface Storage {

    ImageReader getReader();

    ImageWriter getWriter();

}
