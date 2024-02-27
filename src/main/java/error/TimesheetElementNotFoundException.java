package error;


    public class TimesheetElementNotFoundException extends Exception {
        public TimesheetElementNotFoundException() {
            super();
        }

        public TimesheetElementNotFoundException(String message) {
            super(message);
        }

        public TimesheetElementNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public TimesheetElementNotFoundException(Throwable cause) {
            super(cause);
        }

        protected TimesheetElementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

