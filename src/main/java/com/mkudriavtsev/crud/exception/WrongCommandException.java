package main.java.com.mkudriavtsev.crud.exception;

public class WrongCommandException extends Exception {
    public WrongCommandException(String message) {
        super(message);
    }
}
