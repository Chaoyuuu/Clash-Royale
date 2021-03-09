package exception;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class CloneException extends RuntimeException {
    public CloneException() {
    }

    public CloneException(String message) {
        super(message);
    }

    public CloneException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloneException(Throwable cause) {
        super(cause);
    }

    public CloneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
