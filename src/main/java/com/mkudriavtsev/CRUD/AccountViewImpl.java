package main.java.com.mkudriavtsev.CRUD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountViewImpl implements AccountView {
    private BufferedReader reader;
    AccountViewImpl(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public String getCommand() throws IOException {
        return reader.readLine();
    }


}
