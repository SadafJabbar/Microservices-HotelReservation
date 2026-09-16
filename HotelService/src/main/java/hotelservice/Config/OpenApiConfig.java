package hotelservice.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("Hotel Service")
                .version("1.0")
                .description("Hotel Service Microservices practice")
                .contact(new Contact().name("Sadaf Jabbar")));
    }
}
