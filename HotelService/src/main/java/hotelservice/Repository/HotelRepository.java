package hotelservice.Repository;

import hotelservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findAll();
}
