package mx.jovannypcg.jokes.config;

import mx.jovannypcg.jokes.web.JokeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    @Bean
    public RouterFunction<ServerResponse> routerFunction(JokeHandler jokeHandler) {
        LOGGER.info("Initializing router function...");

        return nest(path("/jokes"),
                nest(accept(MediaType.APPLICATION_JSON),
                        route(GET("/"), jokeHandler::findAll))
                                .andRoute(POST("/"), jokeHandler::save));
    }
}
