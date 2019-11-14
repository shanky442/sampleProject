package shl.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import shl.customexceptions.MyCustomException;
import shl.models.ErrorDetails;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> exceptionOccurred(Exception ex) {
        ErrorDetails exceptionResp = new ErrorDetails(ex.getMessage(), "Something doesnt seem to be right");
        return new ResponseEntity<ErrorDetails>(exceptionResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MyCustomException.class)
    public final ResponseEntity<ErrorDetails> fileSizeException(MyCustomException ex) {
        ErrorDetails exceptionResp = new ErrorDetails(ex.getMessage(), "");
        return new ResponseEntity<ErrorDetails>(exceptionResp, HttpStatus.BAD_REQUEST);
    }
}
