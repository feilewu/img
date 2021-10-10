package github.resources.img.image;

import java.io.IOException;

public interface ImageTransmitter {

    void writeImage(LocalBaseImage localBaseImage) throws IOException;

}
