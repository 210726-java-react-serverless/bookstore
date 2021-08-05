package com.revature.bookstore.documents;

import java.util.Objects;

public class StandardFormat extends BookFormat {

    private int stockCount;

    public StandardFormat(BookMedium medium, double price) {
        super(medium, price);
    }

    public StandardFormat(BookMedium medium, double price, int stockCount) {
        this(medium, price);
        this.stockCount = stockCount;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardFormat that = (StandardFormat) o;
        return stockCount == that.stockCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockCount);
    }

    @Override
    public String toString() {
        return "StandardFormat{" +
                "medium=" + medium +
                ", price=" + price +
                ", stockCount=" + stockCount +
                '}';
    }

}
