package exceptions;

public class IncompleteMovieInputException extends Exception {
	
	public IncompleteMovieInputException() {
		super("The inputs for this movie listing are incomplete. Unable to create movie listing.");
	}
}
