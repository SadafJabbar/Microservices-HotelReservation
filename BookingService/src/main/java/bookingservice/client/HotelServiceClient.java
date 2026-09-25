package bookingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HotelServiceClient {
    RestTemplate restTemplate = new RestTemplate();


    @Value("${hotelservice.url}")
    private String url;

    public Boolean getByRoomIdIfAvailable(final Long id) {

        return restTemplate.getForObject(
                url + "/Room/Available/" + id, Boolean.class);}



    public Boolean getByHotelIdExists(final Long id){
        return restTemplate.getForObject(url + "/Hotel/Exists/" + id , Boolean.class);
    }

    public String UpdateRoomStatus(Long id){
        return restTemplate.exchange(url + "/Room/update/" + id, HttpMethod.PUT,null,String.class).getBody();
    }


}
