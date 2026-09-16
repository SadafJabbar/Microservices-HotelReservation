package bookingservice.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long id) {
        super("Booking with id: " +id+ " is not found");
    }
}
