package com.revature.bookstore.repos;

import com.revature.bookstore.documents.AppUser;

public class UserRepository implements CrudRepository<AppUser> {

    public AppUser findUserByCredentials(String username, String password) {
        return null;
    }

    public AppUser findUserByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findById(int id) {
        return null;
    }

    @Override
    public AppUser save(AppUser newUser) {
        return null;
    }

    @Override
    public boolean update(AppUser updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

}
