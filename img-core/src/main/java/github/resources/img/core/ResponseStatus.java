package github.resources.img.core;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/6 18:51
 **/
public enum ResponseStatus {

    TOKEN_EXPIRE(5001,"token过期"),
    TOKEN_ILLEGAL(5002,"token非法")
    ;

    private int code;

    private String message;


    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
