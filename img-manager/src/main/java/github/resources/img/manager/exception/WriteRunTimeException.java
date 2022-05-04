package github.resources.img.manager.exception;

public class WriteRunTimeException extends RuntimeException{
    public WriteRunTimeException(Throwable cause) {
        super(cause);
    }

    public WriteRunTimeException(String message) {
        super(message);
    }
}
