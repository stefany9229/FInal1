package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMoviesServiceClient;
import com.dh.catalogservice.api.client.ISeriesServiceClient;
import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.IProduct;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.rabbitmq.queue.CatalogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private  IMoviesServiceClient iMoviesServiceClient;

    @Autowired
    private ISeriesServiceClient iSeriesServiceClient;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogListener listener;

/*
    @GetMapping("/movies/{genre}")
    ResponseEntity<List<Movie>> getMovieGenre(@PathVariable String genre) {

        return ResponseEntity.ok().body(iMoviesServiceClient.getMovieByGenre(genre));
    }

    @GetMapping("/series/{genre}")
    ResponseEntity<List<Serie>> getSerieGenre(@PathVariable String genre) {

        return ResponseEntity.ok().body(iSeriesServiceClient.getSerieByGenre(genre));
    }

*/
/*
    @GetMapping("/todo/{genre}")
    ResponseEntity<List<IProduct>> getAllGenre(@PathVariable String genre) {

        return ResponseEntity.ok().body(catalogService.listarPorGeneroOffLine(genre));
    }

    */



    @GetMapping("/todoOnline/{genre}")
    ResponseEntity<List<IProduct>> getAllGenreOnline(@PathVariable String genre) {

        return ResponseEntity.ok().body(catalogService.listarPorGeneroOnLine(genre));
    }

    @PostMapping("/create/movie")
    ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        listener.receive(movie);
        return  ResponseEntity.ok(catalogService.createMovie(movie));
    }

    @PostMapping("/create/serie")
    ResponseEntity<Serie> createSerie(@RequestBody Serie serie){
        return  ResponseEntity.ok(catalogService.createSerie(serie));
    }

}
