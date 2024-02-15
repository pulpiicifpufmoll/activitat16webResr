package com.example.activitat16restweb;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.message.internal.QualitySourceMediaType;

import java.util.List;

@Path("/bookings")
@Singleton
public class BookingPOJO {

    private BookingRepoImpl activitat16 = new BookingRepoImpl();

    @GET
    @Produces(QualitySourceMediaType.APPLICATION_JSON)
    public List<Booking> get(){
       return activitat16.getBookings();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int post(Booking b){
        try{
            activitat16.addBooking(b);
            return Response.Status.CREATED.getStatusCode();
        } catch (Exception e){
            return Response.Status.NOT_ACCEPTABLE.getStatusCode();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public int put(Booking b){
        try{
            activitat16.updateBooking(b);
            return Response.Status.CREATED.getStatusCode();
        } catch (Exception e){
            return Response.Status.NOT_MODIFIED.getStatusCode();
        }
    }

    @DELETE
    @Path("/{location_number}")
    public int delete(@PathParam("location_number") String locationNumber){
        try{
            activitat16.deleteBooking(locationNumber);
            return Response.Status.OK.getStatusCode();
        } catch (Exception e){
            return Response.Status.BAD_REQUEST.getStatusCode();
        }
    }
}