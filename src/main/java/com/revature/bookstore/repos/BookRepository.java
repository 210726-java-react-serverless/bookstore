package com.revature.bookstore.repos;

import com.mongodb.client.MongoDatabase;
import com.revature.bookstore.documents.Book;
import com.revature.bookstore.util.ConnectionFactory;

public class BookRepository implements CrudRepository<Book>{

    static MongoDatabase db = null;

    MongoDatabase getDBConnection() {
        if (db == null)
        {
            try {
                db = ConnectionFactory.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return db;
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public Book save(Book newResource) {
        return null;
    }

    @Override
    public boolean update(Book updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
