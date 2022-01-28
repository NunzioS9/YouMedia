package dsbd2122.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
       return builder.routes()
                .route( p ->p
                        .path("/user/**")
                        .uri("http://localhost:9091")
                )
                 .route(p ->
                        p.path("/payment/**")
                                .uri("http://localhost:9092/")
                )
                 .route( p ->
                        p.path("/content/**")
                                .uri("http://localhost:9090/")
                )
                .build();
    }
}

