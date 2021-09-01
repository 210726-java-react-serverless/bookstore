package com.revature.bookstore.web.dtos;

public class AvailabilityStatus {

    private boolean isAvailable;

    public AvailabilityStatus() {
        super();
    }

    public AvailabilityStatus(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "AvailabilityStatus{" +
                "isAvailable=" + isAvailable +
                '}';
    }

}
