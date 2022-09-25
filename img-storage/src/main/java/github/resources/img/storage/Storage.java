package github.resources.img.storage;

public interface Storage {

    void write(Image image);

    Image read(Image image);

}
