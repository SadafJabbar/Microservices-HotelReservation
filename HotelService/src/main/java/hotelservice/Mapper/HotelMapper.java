package hotelservice.Mapper;


import hotelservice.dtos.HotelRequest;
import hotelservice.dtos.HotelResponse;
import hotelservice.entities.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public Hotel toEntity(HotelRequest request) {
        return Hotel.builder()
                .name(request.name())
                .address(request.address())
                .build();
    }

    public HotelResponse toResponse(Hotel hotel) {
        return new HotelResponse(
                hotel.getHotelId(),
                hotel.getName(),
                hotel.getAddress()
        );
    }
}
