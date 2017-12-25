package mx.jovannypcg.jokes.service;

import mx.jovannypcg.jokes.domain.Joke;
import reactor.core.publisher.Flux;

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
}
