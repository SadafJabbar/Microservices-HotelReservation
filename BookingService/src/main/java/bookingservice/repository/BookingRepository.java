package bookingservice.repository;

import bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findAll();
}
