package github.resources.img.application.utils;


import github.resources.img.core.model.dto.Response;

public class ResponseUtil {

    public static Response ok(){
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response ok(Object o){
        Response response = new Response();
        response.setSuccess(true);
        response.setObj(o);
        return response;
    }

    public static Response fail(String errorCode, String errorMessage){
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static Response fail(String message){
        return fail(null,message);
    }
}
