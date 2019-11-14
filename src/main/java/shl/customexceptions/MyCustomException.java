package shl.customexceptions;

public class MyCustomException extends RuntimeException {
    public MyCustomException(String errorMessage) {
        super(errorMessage);
    }
    public MyCustomException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
