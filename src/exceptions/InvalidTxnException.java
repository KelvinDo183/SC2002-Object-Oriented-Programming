package exceptions;

public class InvalidTxnException extends Exception {
	
    public InvalidTxnException() {
        super("This transaction is not valid.");
    }
}
