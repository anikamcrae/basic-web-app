package tutorial.core.services.exceptions;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class BlogNotFoundException extends RuntimeException{

    public BlogNotFoundException() {
        super();
    }

    public BlogNotFoundException(Throwable cause) {
        super(cause);
    }

    public BlogNotFoundException(String message) {
        super(message);
    }

    public BlogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
