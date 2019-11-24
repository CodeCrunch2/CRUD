package main.java.com.mkudriavtsev.CRUD;

import java.io.IOException;

public interface AccountView {
    void print(String s);
    String getCommand() throws IOException;
}
