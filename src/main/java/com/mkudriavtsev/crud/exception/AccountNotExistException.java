package main.java.com.mkudriavtsev.crud.exception;

public class AccountNotExistException extends Exception{
    public AccountNotExistException(String message) {
        super(message);
    }
}
