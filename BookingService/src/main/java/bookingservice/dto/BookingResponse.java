package bookingservice.dto;

import bookingservice.enums.BookingStatus;
import lombok.Builder;
;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record BookingResponse(
        Long bookingId,
        Long hotelId,

        Long roomId,

        String guestName,

        LocalDate checkIn,
        LocalDate checkOut,
        BookingStatus status
)implements Serializable {
}
