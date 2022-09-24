package github.resources.img.application.web.advice;

import github.resources.img.application.dto.Response;
import github.resources.img.application.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class CustomExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Response handle(Exception e){
        return ResponseUtil.fail(e.getMessage());
    }

}
