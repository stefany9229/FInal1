package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMoviesServiceClient;
import com.dh.catalogservice.api.client.ISeriesServiceClient;
import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private  IMoviesServiceClient iMoviesServiceClient;

    @Autowired
    private ISeriesServiceClient iSeriesServiceClient;





    @GetMapping("/movies/{genre}")
    ResponseEntity<List<Movie>> getMovieGenre(@PathVariable String genre) {

        return ResponseEntity.ok().body(iMoviesServiceClient.getMovieByGenre(genre));
    }

    @GetMapping("/series/{genre}")
    ResponseEntity<List<Serie>> getSerieGenre(@PathVariable String genre) {

        return ResponseEntity.ok().body(iSeriesServiceClient.getSerieByGenre(genre));
    }

}
