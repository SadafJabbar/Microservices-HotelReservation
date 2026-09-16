package apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class HotelServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> hotelRoutes() {

        return route("hotel-service")
                .route(
                        RequestPredicates.GET("/api/v1/hotelService/**"),
                        http()
                )
                .before(uri("http://localhost:8080"))
                .filter(
                        CircuitBreakerFilterFunctions.circuitBreaker(
                                "hotelServiceCircuitBreaker",
                                URI.create("forward:/fallbackRouteHotel")
                        )
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRouteHotel() {

        return route("fallbackRouteHotel")
                .POST(
                        "/fallbackRouteHotel",
                        request -> ServerResponse
                                .status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body("Hotel Service is down")
                )
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> hotelServiceApiDocs() {

        return route("hotel-service-api-docs")
                .GET(
                        "/docs/hotelservice/v3/api-docs",
                        http()
                )
                .before(uri("http://localhost:8080"))
                .before(rewritePath(
                        "/docs/hotelservice/v3/api-docs",
                        "/v3/api-docs"
                ))
                .build();
    }}