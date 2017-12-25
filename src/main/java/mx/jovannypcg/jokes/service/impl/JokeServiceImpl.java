package mx.jovannypcg.jokes.service.impl;

import mx.jovannypcg.jokes.domain.Joke;
import mx.jovannypcg.jokes.repository.JokeRepository;
import mx.jovannypcg.jokes.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class JokeServiceImpl implements JokeService {
    private JokeRepository jokeRepository;

    @Autowired
    public JokeServiceImpl(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public Flux<Joke> findAll() {
        return jokeRepository.findAll();
    }
}
