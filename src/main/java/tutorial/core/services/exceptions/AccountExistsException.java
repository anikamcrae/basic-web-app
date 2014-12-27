package tutorial.core.services.exceptions;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class AccountExistsException extends RuntimeException{
    public AccountExistsException() { super(); }

    public AccountExistsException(Throwable cause) {
        super(cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
