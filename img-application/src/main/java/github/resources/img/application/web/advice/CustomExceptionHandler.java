package github.resources.img.application.web.advice;

import github.resources.img.application.model.dto.Response;
import github.resources.img.application.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class CustomExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Response handle(Exception e){
        log.error("catch error",e);
        return ResponseUtil.fail(e.getMessage());
    }

}
