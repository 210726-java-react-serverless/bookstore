package com.revature.bookstore.documents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserFavorites {

    private String id;
    private String username;
    private List<Book> favorites = new ArrayList<>();

    public UserFavorites(String username) {
        this.username = username;
    }

    public UserFavorites(String id, String username) {
        this(username);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Book> favorites) {
        this.favorites = favorites;
    }

    public UserFavorites addFavorites(Book... books) {
        favorites.addAll(Arrays.asList(books));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavorites that = (UserFavorites) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(favorites, that.favorites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, favorites);
    }

    @Override
    public String toString() {
        return "UserFavorites{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", favorites=" + favorites +
                '}';
    }

}
