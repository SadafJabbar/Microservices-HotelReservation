package bookingservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookingRequest(
        @NotNull
        Long hotelId,

        @NotNull
        Long roomId,

        @Size(min = 3, max = 100)
        String guestName,

        LocalDate checkIn,
        LocalDate checkOut
        ) {
}
