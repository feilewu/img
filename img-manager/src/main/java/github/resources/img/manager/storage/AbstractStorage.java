package github.resources.img.manager.storage;

import github.resources.img.manager.ImageReader;
import github.resources.img.manager.ImageWriter;

public abstract class AbstractStorage implements Storage {

    private ImageReader imageReader;

    private ImageWriter imageWriter;

    @Override
    public ImageReader getReader() {
        return imageReader;
    }

    @Override
    public ImageWriter getWriter() {
        return imageWriter;
    }

    public void setImageReader(ImageReader imageReader) {
        this.imageReader = imageReader;
    }

    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }
}
