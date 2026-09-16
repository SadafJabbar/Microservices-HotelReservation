package hotelservice.dtos;

import hotelservice.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RoomRequest(
        @NotNull
        Long roomNo,

        @NotNull
        RoomType type,

        @NotNull
        Long hotelId
) {
}
