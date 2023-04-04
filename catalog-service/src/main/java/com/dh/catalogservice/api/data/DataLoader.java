package com.dh.catalogservice.api.data;

import com.dh.catalogservice.api.client.IMoviesServiceClient;
import com.dh.catalogservice.domain.model.Movie;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


    @Component
    public class DataLoader implements ApplicationRunner {

        private final IMoviesServiceClient repository;

        public DataLoader(IMoviesServiceClient repository) {
            this.repository = repository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {

        }
    }


