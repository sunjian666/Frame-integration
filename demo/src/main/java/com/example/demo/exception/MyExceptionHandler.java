package com.example.demo.exception;

import com.example.demo.util.JsonResonse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyExceptionHandler implements ErrorController{
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public JsonResonse handleError(HttpServletRequest request){

        JsonResonse resonse = new JsonResonse();
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 403){
            resonse.setCode(ErrorCode.NO_ACCESS.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.NO_ACCESS.getMessage());
        }
        return resonse;
    }

    /*@ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyException(AccessDeniedException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("message", "xxx");
        result.put("error type", "MyException");
        return result;
    }*/

}
