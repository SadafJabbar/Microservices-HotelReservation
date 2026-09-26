package hotelservice.service;

import hotelservice.Mapper.HotelMapper;
import hotelservice.Repository.HotelRepository;
import hotelservice.dtos.HotelRequest;
import hotelservice.dtos.HotelResponse;
import hotelservice.entities.Hotel;

import hotelservice.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Autowired
    public HotelService(final HotelRepository hotelRepository,
                        final HotelMapper hotelMapper){
        this.hotelRepository=hotelRepository;
        this.hotelMapper=hotelMapper;
    }

    @Cacheable("hotels")
    public HotelResponse hotelById(Long id){
        System.out.println("db hit");
        Hotel hotel= hotelRepository.findById(id).orElseThrow(()-> new HotelNotFoundException(id));
        return hotelMapper.toResponse(hotel);
    }

    public List<HotelResponse> hotels(){
        List<HotelResponse> hotelResponses=new ArrayList<>();
        for(Hotel hotel:hotelRepository.findAll()){
            HotelResponse hotelResponse=hotelMapper.toResponse(hotel);
            hotelResponses.add(hotelResponse);
        }
        return hotelResponses;
    }



    public Boolean hotelByIdExists(Long id){
        Hotel hotel= hotelRepository.findById(id).orElse(null);
        if(hotel==null){
            return false;
        }
        return true;
    }

    public HotelResponse createHotel(HotelRequest hotelRequest){
        Hotel hotel=hotelMapper.toEntity(hotelRequest);
        hotelRepository.save(hotel);
        return hotelMapper.toResponse(hotel);
    }

}


