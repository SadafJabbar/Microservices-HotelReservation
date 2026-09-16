package notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationEvent {

    private Long bookingId;
    private Long roomId;
    private String guestName;
    private String message;
}
