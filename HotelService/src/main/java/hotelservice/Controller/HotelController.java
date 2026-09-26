package hotelservice.Controller;

import hotelservice.dtos.HotelRequest;
import hotelservice.dtos.HotelResponse;
import hotelservice.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotelService")
public class HotelController {

    private final HotelService hotelService;
    @Autowired
    public HotelController(HotelService hotelService){
        this.hotelService=hotelService;
    }


    @Operation(summary = "Create Hotel record")
    @PostMapping("/Hotel")
    public HotelResponse createHotel(@Valid @RequestBody HotelRequest hotelRequest){
        return hotelService.createHotel(hotelRequest);
    }

    @Operation(summary = "Get Hotel Record By Id")
    @GetMapping("/Hotel/{id}")
    public HotelResponse hotelById(@PathVariable ("id") Long id){
        return hotelService.hotelById(id);
    }
    @Operation(summary = "Get All Hotel records")
    @GetMapping("/Hotels")
    public List<HotelResponse> hotels(){
        return hotelService.hotels();
    }


    @Operation(summary = "Get Boolean Response By Existence")
    @GetMapping("/Hotel/Exists/{id}")
    public Boolean hotelByIdExists(@PathVariable ("id") Long id){
        return hotelService.hotelByIdExists(id);
    }

}
