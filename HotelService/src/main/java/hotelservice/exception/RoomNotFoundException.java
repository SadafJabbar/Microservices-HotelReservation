package hotelservice.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Long id) {
        super("Room with id: " +id+ " is not found");
    }
}
