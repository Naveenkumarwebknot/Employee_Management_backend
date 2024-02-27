package error;

public class EmloyeeNotFoundException extends Exception {
    public EmloyeeNotFoundException() {
        super();
    }

    public EmloyeeNotFoundException(String message) {
        super(message);
    }

    public EmloyeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmloyeeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EmloyeeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
