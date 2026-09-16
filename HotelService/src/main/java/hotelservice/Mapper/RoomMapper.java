package hotelservice.Mapper;


import hotelservice.dtos.RoomRequest;
import hotelservice.dtos.RoomResponse;
import hotelservice.entities.Hotel;
import hotelservice.entities.Room;
import hotelservice.enums.RoomStatus;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public Room toEntity(RoomRequest request, Hotel hotel) {
        return Room.builder()
                .roomNo(request.roomNo())
                .type(request.type())
                .hotel(hotel)
                .status(RoomStatus.AVAILABLE)
                .build();
    }

    public RoomResponse toResponse(Room room) {
        return new RoomResponse(
                room.getRoomId(),
                room.getRoomNo(),
                room.getType(),
                room.getHotel().getHotelId(),
                room.getStatus()
        );
    }
}
