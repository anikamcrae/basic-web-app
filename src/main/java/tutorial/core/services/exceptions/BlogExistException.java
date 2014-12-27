package tutorial.core.services.exceptions;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class BlogExistException extends RuntimeException{

    public BlogExistException() {
        super();
    }

    public BlogExistException(Throwable cause) {
        super(cause);
    }

    public BlogExistException(String message) {
        super(message);
    }

    public BlogExistException(String message, Throwable cause) {
        super(message, cause);
    }


}
