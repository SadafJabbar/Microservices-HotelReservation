package hotelservice.dtos;

import hotelservice.enums.RoomStatus;
import hotelservice.enums.RoomType;
import lombok.Builder;

@Builder
public record RoomResponse(
        Long roomId,
        Long roomNo,
        RoomType type,
        Long hotelId,
        RoomStatus status
) {
}
