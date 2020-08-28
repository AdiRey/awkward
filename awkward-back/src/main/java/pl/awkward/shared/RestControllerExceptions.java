package pl.awkward.shared;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.exceptions.OperationNotAllowedException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice
public class RestControllerExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateException.class)
    public String handleDuplicateExceptions(DuplicateException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PropertyReferenceException.class, IllegalArgumentException.class,
            NumberFormatException.class, DataIntegrityViolationException.class})
    public String handlePropertyException(RuntimeException ex) {
        if (ex instanceof DataIntegrityViolationException) {
            DataIntegrityViolationException dExp = (DataIntegrityViolationException) ex;
            return Objects.requireNonNull(dExp.getRootCause()).getMessage();
        }
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(OperationNotAllowedException.class)
    public String handleOperationNotAllowedException(OperationNotAllowedException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException ex) {
        return ex.getMessage();
    }

}
