package hotelservice.service;


import hotelservice.Mapper.RoomMapper;
import hotelservice.Repository.HotelRepository;
import hotelservice.Repository.RoomRepository;
import hotelservice.dtos.RoomRequest;
import hotelservice.dtos.RoomResponse;
import hotelservice.entities.Hotel;
import hotelservice.entities.Room;
import hotelservice.enums.RoomStatus;
import hotelservice.exception.HotelNotFoundException;
import hotelservice.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final HotelRepository hotelRepository;


    @Autowired
    public RoomService(final RoomRepository roomRepository,
                       final RoomMapper roomMapper,
                       final HotelRepository hotelRepository){
        this.roomRepository=roomRepository;
        this.roomMapper=roomMapper;
        this.hotelRepository=hotelRepository;
    }

    @Cacheable("rooms")
    public RoomResponse roomById(Long id){
     Room room =roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    return roomMapper.toResponse(room);}

    public Boolean roomByIdIfAvailable(Long id){
        Room room= roomRepository.findById(id).orElseThrow(() -> new RuntimeException("room not found with id:"+id));
        if(room.getStatus()== RoomStatus.AVAILABLE){
            return true;
        }
        return false;
    }



    public List<RoomResponse> Rooms(){
        List<RoomResponse> roomResponses=new ArrayList<>();
        for(Room room:roomRepository.findAll()){
            RoomResponse response=roomMapper.toResponse(room);
            roomResponses.add(response);
        }
        return roomResponses;
    }

    @CacheEvict(value = "rooms",key = "#id")
    public String updateRoom(Long id){
        Room room=roomRepository.findById(id).orElse(null);
        room.setStatus(RoomStatus.BOOKED);
        roomRepository.save(room);
        return "Room has been booked";
    }

    public RoomResponse createRoom(RoomRequest request){
        Hotel hotel=hotelRepository.findById(request.hotelId()).orElseThrow(()-> new HotelNotFoundException(request.hotelId()));
        Room room=roomMapper.toEntity(request,hotel);
        roomRepository.save(room);
       return roomMapper.toResponse(room);

    }
}
