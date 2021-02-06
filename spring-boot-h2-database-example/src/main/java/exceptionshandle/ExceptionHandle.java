package exceptionshandle;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javatpoint.controller.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Error> mapException(UserNotFoundException ex) {
		Error error = new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
