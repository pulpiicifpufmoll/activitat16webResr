package com.example.activitat16restweb;

import java.util.List;

public interface BookingRepo {
    List<Booking> getBookings();
    void addBooking(Booking b);
    void updateBooking(Booking b);
    void deleteBooking(String id);
}
