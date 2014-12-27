package tutorial.core.services.exceptions;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException() { super(); };

    public AccountDoesNotExistException(Throwable cause) { }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
