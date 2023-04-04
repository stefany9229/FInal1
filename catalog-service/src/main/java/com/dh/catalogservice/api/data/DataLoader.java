package com.dh.catalogservice.api.data;


import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.domain.model.Movie;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


    @Component
    public class DataLoader implements ApplicationRunner {

        private final IMovieRepository repository;

        public DataLoader(IMovieRepository repository) {
            this.repository = repository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            repository.save(new Movie(1L, "filme", "terror", "what"));
        }
    }


