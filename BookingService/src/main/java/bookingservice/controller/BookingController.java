package bookingservice.controller;

import bookingservice.dto.BookingResponse;
import bookingservice.dto.BookingRequest;
import bookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    private  final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }




    @Operation(summary = "Create Booking record")
    @PostMapping(consumes = "application/json",produces = "application/json",path = "/booking")
    public BookingResponse createBooking(@Valid @RequestBody BookingRequest bookingRequest){
       return bookingService.createBooking(bookingRequest);
    }

    @Operation(summary = "Get  Booking record  By Id")
    @GetMapping("/booking/{id}")
    public BookingResponse bookingById(@PathVariable ("id") Long id){
        return bookingService.bookingById(id);
    }

    @Operation(summary = "Get All  Booking records")
    @GetMapping("/booking")
    public List<BookingResponse> bookings(){
        return bookingService.bookings();
    }
}
