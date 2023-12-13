package blogappapis.blogapplication.exception;

import blogappapis.blogapplication.payloads.apiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<apiResponse> resourceNotFoundHandler(ResourceNotFoundException ex){
        String message= ex.getMessage();
        apiResponse apiResponse=new apiResponse(message,false);
        return new ResponseEntity<apiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errorMap=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName=((FieldError)objectError).getField();
            String message=objectError.getDefaultMessage();
            errorMap.put(fieldName,message);
        });

        return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
    }

}
