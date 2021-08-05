package com.revature.bookstore.documents;

public abstract class BookFormat {

    protected BookMedium medium;
    protected double price;

    public BookFormat(BookMedium medium, double price) {
        this.medium = medium;
        this.price = price;
    }

    public BookMedium getMedium() {
        return medium;
    }

    public void setMedium(BookMedium medium) {
        this.medium = medium;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
