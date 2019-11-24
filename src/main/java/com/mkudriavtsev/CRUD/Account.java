package main.java.com.mkudriavtsev.CRUD;

public class Account {
    private long id;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Account(long id, String data) {
        this.id = id;
        this.data = data;
    }
}
