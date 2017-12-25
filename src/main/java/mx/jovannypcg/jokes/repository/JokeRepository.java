package mx.jovannypcg.jokes.repository;

import mx.jovannypcg.jokes.domain.Joke;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JokeRepository extends ReactiveCassandraRepository<Joke, UUID> {
    // ... no content so far
}
