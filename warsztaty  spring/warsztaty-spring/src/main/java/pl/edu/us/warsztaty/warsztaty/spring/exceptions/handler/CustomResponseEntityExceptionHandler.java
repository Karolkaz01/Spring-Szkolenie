package pl.edu.us.warsztaty.warsztaty.spring.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.us.warsztaty.warsztaty.spring.exceptions.UserAlreadyExistException;
import pl.edu.us.warsztaty.warsztaty.spring.exceptions.UserNotFoundException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Clock clock;

    public CustomResponseEntityExceptionHandler(Clock clock) {
        this.clock = clock;
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(clock),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(UserAlreadyExistException e, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(clock),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> listOfErrors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(
                e -> listOfErrors.add(listOfErrors.size()+1 + ". " + e.getDefaultMessage())
        );

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(clock),
                "Validation faild",
                listOfErrors.toString()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
