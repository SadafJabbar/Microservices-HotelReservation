package hotelservice.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) {
        super("Hotel with id: " +id+ " is not found");
    }
}
