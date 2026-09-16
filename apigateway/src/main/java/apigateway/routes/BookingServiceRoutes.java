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
public class BookingServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> bookingRoutes() {

        return route("booking-service")
                .route(
                        RequestPredicates.POST("/api/v1/booking"),
                        http()
                )
                .before(uri("http://localhost:8081"))
                .filter(
                        CircuitBreakerFilterFunctions.circuitBreaker(
                                "bookingServiceCircuitBreaker",
                                URI.create("forward:/fallbackRoute")
                        )
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {

        return route("fallbackRoute")
                .POST(
                        "/fallbackRoute",
                        request -> ServerResponse
                                .status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body("Booking Service is down")
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> bookingServiceApiDocs() {

        return route("booking-service-api-docs")
                .GET(
                        "/docs/bookingservice/v3/api-docs",
                        http()
                )
                .before(uri("http://localhost:8081"))
                .before(rewritePath(
                        "/docs/bookingservice/v3/api-docs",
                        "/v3/api-docs"
                ))
                .build();
    }
}