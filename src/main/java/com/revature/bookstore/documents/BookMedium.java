package com.revature.bookstore.documents;

public enum BookMedium {

    HARDCOVER("Hardcover"), PAPERBACK("Paperback"), LIBRARY_BINDING("Library Binding"),
    EBOOK("Ebook"), AUDIOBOOK("Audiobook");

    private String name;

    BookMedium(String name) {
        this.name = name;
    }

    public static BookMedium fromString(String name) {

        for (BookMedium medium : BookMedium.values()) {
            if (medium.name.equals(name)) {
                return medium;
            }
        }

        return null;

    }

    @Override
    public String toString() {
        return name;
    }

}
