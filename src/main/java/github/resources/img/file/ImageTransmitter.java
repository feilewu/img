package github.resources.img.file;

import java.io.IOException;

public interface ImageTransmitter {

    void writeImage(Image image) throws IOException;

}
