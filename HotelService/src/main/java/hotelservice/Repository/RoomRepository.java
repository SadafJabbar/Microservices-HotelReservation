package hotelservice.Repository;

import hotelservice.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
