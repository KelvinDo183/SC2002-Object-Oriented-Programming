package exceptions;

public class ReleasePastEndException extends Exception {
	
	public ReleasePastEndException() {
		super("This movie is invalid because its release date is past its scheduled end date.");
	}

}
