package com.revature.bookstore.datasource.documents;

public class ElectronicFormat extends BookFormat {

    public ElectronicFormat(BookMedium medium, double price) {
        super(medium, price);
    }

    @Override
    public String toString() {
        return "ElectronicFormat{" +
                "medium=" + medium +
                ", price=" + price +
                '}';
    }

}
