package main.java.com.mkudriavtsev.CRUD;

public interface AccountRepository {
    void save (Account account) throws IDExistException;
    Account get(long id) throws IDNotExistException;
    void delete(long id) throws IDNotExistException;
    boolean isIDExist(long id);
}
