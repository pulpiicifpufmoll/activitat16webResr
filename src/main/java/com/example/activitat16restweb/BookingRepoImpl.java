package com.example.activitat16restweb;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookingRepoImpl implements BookingRepo{
    private final String pathBookingsXml = "./resources/bookings.xml";
    private List<Booking> bookings;

    public BookingRepoImpl() {
        this.bookings = loadBookingsToLocalBD();
    }

    private List<Booking> loadBookingsToLocalBD(){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(new File(pathBookingsXml), xmlMapper.getTypeFactory().constructCollectionType(List.class, Booking.class));
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getBookings(){
        return this.bookings;
    }

    public void addBooking(Booking b){
        this.bookings.add(b);
    }

    public void updateBooking(Booking bookingToUpdate){
        List<Booking> listToUpdate = this.bookings;
        for (Booking b : listToUpdate){
            if (b.getLocation_number().equals(bookingToUpdate.getLocation_number())){
                listToUpdate.remove(b);
                listToUpdate.add(bookingToUpdate);
            }
            setBookings(listToUpdate);
        }
    }

    public void deleteBooking(String id){
        List<Booking> listToUpdate = this.bookings;
        for(Booking b : listToUpdate){
            if (b.getLocation_number().equals(id)){
                listToUpdate.remove(b);
                setBookings(listToUpdate);
            }
        }
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
