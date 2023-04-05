package com.dh.catalogservice.api.repository;


import com.dh.catalogservice.domain.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;




public interface IMovieRepository extends MongoRepository<Movie, Long> {
    List<Movie> findAllByGenre(String genre);

}
