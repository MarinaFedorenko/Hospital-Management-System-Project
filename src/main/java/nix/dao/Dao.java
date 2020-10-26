package nix.dao;

import java.util.List;

public interface Dao<T> {

    List<T> findAll();
    T findById(Long id);
    void insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
