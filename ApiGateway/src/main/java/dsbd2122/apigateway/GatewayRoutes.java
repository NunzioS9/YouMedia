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
                .route(r ->
                        r.path("/user/**")
                                .uri("http://appcustomer:9091/")
                )
                .route(r ->
                        r.path("/payment/**")
                                .uri("http://apppayment:9092/")
                )
                .route(r ->
                        r.path("/content/**")
                                .uri("http://appresource:9090/")
                )
                .build();
    }
}

