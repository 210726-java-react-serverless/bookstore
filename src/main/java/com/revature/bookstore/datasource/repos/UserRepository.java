package com.revature.bookstore.datasource.repos;

import com.revature.bookstore.datasource.documents.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {

//    @Query("{username: ?}") // You can optionally provide your own custom queries using this annotation
    AppUser findAppUserByUsernameAndPassword(String username, String password);
    AppUser findAppUserByUsername(String username);
    AppUser findAppUserByEmail(String email);

}
