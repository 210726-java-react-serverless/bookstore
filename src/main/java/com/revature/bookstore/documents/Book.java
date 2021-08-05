package com.revature.bookstore.documents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {

    private String id;
    private String isbn;
    private String title;
    private String publisher;
    private List<String> authors = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<BookFormat> availableFormats = new ArrayList<>();

    public Book(String isbn, String title, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
    }

    public Book(String id, String isbn, String title, String publisher) {
        this(isbn, title, publisher);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<BookFormat> getAvailableFormats() {
        return availableFormats;
    }

    public void setAvailableFormats(List<BookFormat> availableFormats) {
        this.availableFormats = availableFormats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id)
                && Objects.equals(isbn, book.isbn)
                && Objects.equals(title, book.title)
                && Objects.equals(publisher, book.publisher)
                && Objects.equals(authors, book.authors)
                && Objects.equals(genres, book.genres)
                && Objects.equals(availableFormats, book.availableFormats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, publisher, authors, genres, availableFormats);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", availableFormats=" + availableFormats +
                '}';
    }

}
