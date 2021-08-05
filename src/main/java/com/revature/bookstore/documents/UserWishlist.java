package com.revature.bookstore.documents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserWishlist {

    private String id;
    private String username;
    private List<Book> wishlist = new ArrayList<>();

    public UserWishlist(String username) {
        this.username = username;
    }

    public UserWishlist(String id, String username) {
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

    public List<Book> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Book> wishlist) {
        this.wishlist = wishlist;
    }

    public UserWishlist addToWishlist(Book... books) {
        wishlist.addAll(Arrays.asList(books));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWishlist that = (UserWishlist) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(wishlist, that.wishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, wishlist);
    }

    @Override
    public String toString() {
        return "UserWishlist{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", wishlist=" + wishlist +
                '}';
    }

}
