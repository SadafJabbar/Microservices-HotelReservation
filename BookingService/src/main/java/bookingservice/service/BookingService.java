package bookingservice.service;

import bookingservice.entity.Booking;
import bookingservice.event.BookingEvent;
import bookingservice.client.HotelServiceClient;
import bookingservice.dto.BookingRequest;
import bookingservice.dto.BookingResponse;
import bookingservice.enums.BookingStatus;
import bookingservice.exception.BookingNotFoundException;
import bookingservice.mapper.BookingMapper;
import bookingservice.repository.BookingRepository;
import org.springframework.cache.annotation.Cacheable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingService {
    private  final BookingRepository bookingRepository;
    private final HotelServiceClient hotelServiceClient;
    private final BookingMapper bookingMapper;
    private final KafkaTemplate<String ,BookingEvent> kafkaTemplate;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          HotelServiceClient hotelServiceClient,
                          BookingMapper bookingMapper,
                          KafkaTemplate<String,BookingEvent> kafkaTemplate){
        this.bookingRepository=bookingRepository;
        this.hotelServiceClient=hotelServiceClient;
        this.bookingMapper=bookingMapper;
        this.kafkaTemplate=kafkaTemplate;
    }

    @Cacheable("bookings")
    public BookingResponse bookingById(Long id){
        System.out.println("db hit");
        Booking booking= bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
        return bookingMapper.transformToResponse(booking);
    }

    public List<BookingResponse> bookings(){
        List<BookingResponse> bookings=new ArrayList<>();
        for(Booking booking:bookingRepository.findAll()){
            BookingResponse bookingResponse=bookingMapper.transformToResponse(booking);
            bookings.add(bookingResponse);
        }

        return bookings;
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Boolean roomStatus =hotelServiceClient.getByRoomIdIfAvailable(bookingRequest.roomId());
        Boolean hotelStatus =hotelServiceClient.getByHotelIdExists(bookingRequest.hotelId());
        if (!hotelStatus) {
            throw new RuntimeException("Hotel does not exist");}
        if (!roomStatus) {
            throw new RuntimeException("Room is not available");}
        if (!bookingRequest.checkIn().isBefore(bookingRequest.checkOut())) {
            throw new RuntimeException("Check-in must be before check-out");}
        Booking booking = bookingMapper.transformToEntity(bookingRequest);
        booking.setStatus(BookingStatus.CONFIRMED);
        hotelServiceClient.UpdateRoomStatus(bookingRequest.roomId());
        Booking savedBooking = bookingRepository.save(booking);
        BookingEvent bookingEvent=bookingMapper.transformToBookingEvent(booking);
        kafkaTemplate.send("Booking",bookingEvent);
        log.info("booking sent to kafka :{}",bookingEvent);
        return bookingMapper.transformToResponse(savedBooking);

    }
}
