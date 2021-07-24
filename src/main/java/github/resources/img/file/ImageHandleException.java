package github.resources.img.file;

import java.io.IOException;

public class ImageHandleException extends IOException {

    public ImageHandleException() {
        super();
    }

    public ImageHandleException(String message) {
        super(message);
    }

    public ImageHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageHandleException(Throwable cause) {
        super(cause);
    }
}
