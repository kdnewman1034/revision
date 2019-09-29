package revision.exception;

/**
 * Custom Exception class for the revision comparator
 * 
 * @author Kevin
 *
 */
public class RevisionException extends Exception{

	private static final long serialVersionUID = 5359043800428428900L;

	public RevisionException(String message) {
		super(message);
	}
}
