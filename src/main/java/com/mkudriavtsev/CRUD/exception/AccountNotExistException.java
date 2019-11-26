package main.java.com.mkudriavtsev.CRUD.exception;

public class AccountNotExistException extends Exception{
    public AccountNotExistException(String message) {
        super(message);
    }
}
