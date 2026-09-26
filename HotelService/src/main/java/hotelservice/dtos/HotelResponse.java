package hotelservice.dtos;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record HotelResponse(
        Long hotelId,
        String name,
        String address
) implements Serializable {
}
