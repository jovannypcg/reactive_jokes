package mx.jovannypcg.jokes.web;

import mx.jovannypcg.jokes.domain.Joke;
import mx.jovannypcg.jokes.domain.JokeDTO;
import mx.jovannypcg.jokes.service.JokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
public class JokeHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JokeHandler.class);

    private JokeService jokeService;

    @Autowired
    public JokeHandler(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Joke> jokes = jokeService.findAll();
        return  ServerResponse.ok().body(jokes, Joke.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<JokeDTO> body = request.bodyToMono(JokeDTO.class);

        return body
                .flatMap(this::toJokeMono)
                .flatMap(jokeService::save)
                .flatMap(savedJoke -> {
                    LOGGER.info("Saved joke: {}", savedJoke);
                    return ServerResponse.created(URI.create("/jokes/" + savedJoke.getId())).build();
                }).switchIfEmpty(ServerResponse.badRequest().build());
    }

    /**
     * Converts the {@link JokeDTO} sent as argument to a <code>Mono<Joke></code> object.
     * @param dto
     * @return
     */
    private Mono<Joke> toJokeMono(JokeDTO dto) {
        Joke joke = new Joke();
        joke.setId(UUID.fromString(dto.getId()));
        joke.setContent(dto.getContent());

        return Mono.just(joke);
    }
}
