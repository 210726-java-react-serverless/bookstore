package com.revature.bookstore.repos;

import com.mongodb.client.MongoDatabase;
import com.revature.bookstore.util.ConnectionFactory;

public interface CrudRepository<T> {

    T findById(int id);
    T save(T newResource);
    boolean update(T updatedResource);
    boolean deleteById(int id);



}
