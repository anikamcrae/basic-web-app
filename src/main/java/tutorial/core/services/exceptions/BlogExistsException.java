package tutorial.core.services.exceptions;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class BlogExistsException extends RuntimeException{

    public BlogExistsException() {
        super();
    }

    public BlogExistsException(Throwable cause) {
        super(cause);
    }

    public BlogExistsException(String message) {
        super(message);
    }

    public BlogExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
