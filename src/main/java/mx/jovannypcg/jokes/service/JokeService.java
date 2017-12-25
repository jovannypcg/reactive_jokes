package mx.jovannypcg.jokes.service;

import mx.jovannypcg.jokes.domain.Joke;
import mx.jovannypcg.jokes.domain.JokeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Domain logic for {@link Joke}.
 */
public interface JokeService {
    /**
     * Gets a stream with all stored jokes.
     *
     * @return Stored jokes.
     */
    Flux<Joke> findAll();

    /**
     * Stores the given joke into database.
     *
     * @param joke Object to be stored.
     * @return Mono with the stored object.
     */
    Mono<Joke> save(Joke joke);
}
