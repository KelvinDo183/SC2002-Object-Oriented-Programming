package exceptions;

public class ExistingMovieException extends Exception {

	public ExistingMovieException() {
		super("This movie already exists. Unable to insert it to list of screening movies");
	}
	
}
