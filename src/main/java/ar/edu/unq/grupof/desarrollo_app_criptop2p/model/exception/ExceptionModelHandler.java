package ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionModelHandler {
   @ExceptionHandler(value = MethodArgumentNotValidException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String > exceptionMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(ex ->
            exceptionMap.put(ex.getField(), ex.getDefaultMessage())
        );

        return exceptionMap;
    }
}
