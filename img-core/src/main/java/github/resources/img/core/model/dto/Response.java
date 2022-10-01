package github.resources.img.core.model.dto;

import lombok.Data;

@Data
public class Response {

    private boolean success;

    private String code;

    private String message;

    private Object obj;

}
