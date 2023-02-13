package API.architecture.javaAPI.api;

import API.architecture.javaAPI.errorHandling.CustomMessageError;
import API.architecture.javaAPI.errorHandling.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    //Logger
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(CustomMessageError.class)
    public ResponseEntity<ErrorMessage> handleCustomMessageErrorException(CustomMessageError exception, WebRequest webRequest) {
        //Logger
        logger.info("Error: " + exception.getMessage());
        //Custom Error Message
        ErrorMessage obj = new ErrorMessage();
        obj.setMessage(exception.getMessage());
        obj.setTimestamp(new Date());

        return new ResponseEntity<>(obj, HttpStatus.NOT_ACCEPTABLE);
    }
}
