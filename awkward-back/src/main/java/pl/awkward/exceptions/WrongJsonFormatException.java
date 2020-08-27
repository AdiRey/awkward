package pl.awkward.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong Json format!")
public class WrongJsonFormatException extends RuntimeException {
}
