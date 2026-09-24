package bookingservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class BookingEvent {
    private Long bookingId;
    private Long hotelId;
    private Long roomId;
    private String guestName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
}
