package mx.jovannypcg.jokes.web;

import mx.jovannypcg.jokes.domain.Joke;
import mx.jovannypcg.jokes.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JokeHandler {
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
        return ServerResponse.created(request.uri()).build();
    }
}
