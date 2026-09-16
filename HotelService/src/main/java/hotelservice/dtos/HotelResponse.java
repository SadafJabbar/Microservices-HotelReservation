package hotelservice.dtos;

import lombok.Builder;

@Builder
public record HotelResponse(
        Long hotelId,
        String name,
        String address
) {
}
