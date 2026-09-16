package bookingservice.mapper;

import bookingservice.entity.Booking;
import bookingservice.event.BookingEvent;
import bookingservice.dto.BookingRequest;
import bookingservice.dto.BookingResponse;
import bookingservice.enums.BookingStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking transformToEntity(BookingRequest bookingRequest){
        return Booking.builder().
                hotelId(bookingRequest.hotelId())
                        .roomId(bookingRequest.roomId())
                .guestName(bookingRequest.guestName())
                .checkIn(bookingRequest.checkIn())
                .checkOut(bookingRequest.checkOut())
                .status(BookingStatus.CONFIRMED)
                .build();
    }

    public BookingResponse transformToResponse(Booking booking){
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .hotelId(booking.getHotelId())
                .roomId(booking.getRoomId())
                .guestName(booking.getGuestName())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .status(booking.getStatus())
                .build();
    }

    public BookingEvent transformToBookingEvent(Booking booking){
        return BookingEvent.builder()
                .bookingId(booking.getBookingId())
                .hotelId(booking.getHotelId())
                .roomId(booking.getRoomId())
                .guestName(booking.getGuestName())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .status(booking.getStatus().name())
                .build();
    }


}
