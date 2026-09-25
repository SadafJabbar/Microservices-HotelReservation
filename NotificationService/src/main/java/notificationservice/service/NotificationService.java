package notificationservice.service;


import bookingservice.event.BookingEvent;
import lombok.extern.slf4j.Slf4j;
import notificationservice.event.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService
{
    @KafkaListener(topics = "Booking",groupId = "notification-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("received booking:{}", bookingEvent);
        NotificationEvent notificationEvent=createNotification(bookingEvent);
        log.info("room has been booked:{}",notificationEvent);
    }

    public NotificationEvent createNotification(BookingEvent bookingEvent){
        return NotificationEvent.builder()
                .bookingId(bookingEvent.getBookingId())
                .roomId(bookingEvent.getRoomId())
                .guestName(bookingEvent.getGuestName())
                .message("booking has been placed")
                .build();
    }
}
