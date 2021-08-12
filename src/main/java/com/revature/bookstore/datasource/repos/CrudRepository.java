package com.revature.bookstore.datasource.repos;

import java.util.List;

public interface CrudRepository<T> {

    List<T> findAll();
    T findById(String id);
    T save(T newResource);
    boolean update(T updatedResource);
    boolean deleteById(int id);

}
