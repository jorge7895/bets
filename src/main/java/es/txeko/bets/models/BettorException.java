package es.txeko.bets.models;

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
