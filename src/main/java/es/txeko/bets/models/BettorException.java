package es.txeko.bets.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BettorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BettorException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BettorException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return String.format(super.getMessage());
	}
}
