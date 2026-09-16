package hotelservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record HotelRequest(

        @NotBlank
        @Size(min = 3, max = 30)
        String name,

        @NotBlank
        @Size(min = 3, max = 100)
        String address
) {
}
