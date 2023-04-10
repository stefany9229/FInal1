package com.example.serieservice.controller;

import com.example.serieservice.model.Serie;
import com.example.serieservice.rabbitmq.queue.SerieSender;
import com.example.serieservice.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vaninagodoy
 */

@RestController
public class SerieController {

    private final SerieService serieService;
    private final SerieSender sender;

    public SerieController(SerieService serieService, SerieSender sender) {
        this.serieService = serieService;
        this.sender = sender;
    }

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/series/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping("/series/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Serie> create(@RequestBody Serie serie) {
        Serie newSerie=serieService.create(serie);
        this.sender.send(newSerie);
        return ResponseEntity.ok(newSerie);
    }
}
