package mx.jovannypcg.jokes.web;

import mx.jovannypcg.jokes.domain.Joke;
import mx.jovannypcg.jokes.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JokeHandler {
    private JokeService jokeService;

    @Autowired
    public JokeHandler(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    public Mono<ServerResponse> findAll() {
        Flux<Joke> jokes = jokeService.findAll();
        return  ServerResponse.ok().body(jokes, Joke.class);
    }
}
