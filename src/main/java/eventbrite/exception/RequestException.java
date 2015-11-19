package eventbrite.exception;

/**
 * Represents an exception that occurred when invoking the Eventbrite APIs.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public class RequestException extends Exception {
    private static final long serialVersionUID = 1L;

    public RequestException() {
    }

    public RequestException(Throwable cause) {
        super(cause);
    }

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
