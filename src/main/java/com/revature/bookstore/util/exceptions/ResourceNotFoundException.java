package com.revature.bookstore.util.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("No resource found using provided search criteria.");
    }
}
