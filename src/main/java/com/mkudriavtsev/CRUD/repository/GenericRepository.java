package main.java.com.mkudriavtsev.CRUD.repository;


import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T t);
    void delete(T t);
    void update(T t);
    ID getNextID();
    List<T> getAll();

}
