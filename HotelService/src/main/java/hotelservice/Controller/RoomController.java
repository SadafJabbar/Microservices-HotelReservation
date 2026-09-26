package hotelservice.Controller;

import hotelservice.dtos.RoomRequest;
import hotelservice.dtos.RoomResponse;
import hotelservice.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotelService")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }

    @Operation(summary = "Create Room record")
    @PostMapping("/Room")
    public RoomResponse createRoom(@Valid @RequestBody RoomRequest request){
        return roomService.createRoom(request);
    }

    @Operation(summary = "Get Room record By Id")
    @GetMapping("/Room/{id}")
    public RoomResponse roomById(@PathVariable("id") Long id){
        return roomService.roomById(id);
    }

    @Operation(summary = "Get All Room records")
    @GetMapping("/Rooms")
    public List<RoomResponse> rooms(){
        return roomService.Rooms();
    }

    @Operation(summary = "Get Room record by Availability(Boolean)")
    @GetMapping("/Room/Available/{id}")
    public Boolean roomByIdIfAvaileble(@PathVariable("id") Long id){

        return roomService.roomByIdIfAvailable(id);
    }

    @Operation(summary = "Update Room record")
    @PutMapping("/Room/update/{id}")
    public String updateRoom(@PathVariable ("id") Long id){
        return roomService.updateRoom(id);
    }


}
