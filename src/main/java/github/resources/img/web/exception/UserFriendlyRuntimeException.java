package github.resources.img.web.exception;

public class UserFriendlyRuntimeException extends RuntimeException{
    public UserFriendlyRuntimeException() {
        super();
    }

    public UserFriendlyRuntimeException(String message) {
        super(message);
    }

    public UserFriendlyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFriendlyRuntimeException(Throwable cause) {
        super(cause);
    }

    protected UserFriendlyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
