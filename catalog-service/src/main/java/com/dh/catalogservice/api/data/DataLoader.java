package com.dh.catalogservice.api.data;


import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.api.repository.ISerieRepository;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
    public class DataLoader implements ApplicationRunner {

        private final IMovieRepository movieRepository;
        private final ISerieRepository serieRepository;

        public DataLoader(IMovieRepository movieRepository, ISerieRepository serieRepository) {
            this.movieRepository = movieRepository;
            this.serieRepository = serieRepository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            movieRepository.save(new Movie(1L, "filme", "terror", "what"));


            String baseUrl = "www.netflix.com/series";

            List<Serie.Season.Chapter> serieASeasonAChapters = List.of(
                    new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/terror/1/season/1/chapter/1"),
                    new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/terror/1/season/1/chapter/2")
            );

            List<Serie.Season.Chapter> serieASeasonBChapters = List.of(
                    new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/terror/1/season/2/chapter/1"),
                    new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/terror/1/season/2/chapter/2")
            );

            List<Serie.Season> serieASeasons = List.of(
                    new Serie.Season(1, serieASeasonAChapters),
                    new Serie.Season(2, serieASeasonBChapters)
            );

            //Serie B comedia
            List<Serie.Season.Chapter> serieBSeasonAChapters = List.of(
                    new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/1/chapter/1"),
                    new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/1/chapter/2")
            );

            List<Serie.Season.Chapter> serieBSeasonBChapters = List.of(
                    new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/2/chapter/1"),
                    new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/2/chapter/2")
            );

            List<Serie.Season> serieBSeasons = List.of(
                    new Serie.Season(1, serieBSeasonAChapters),
                    new Serie.Season(2, serieBSeasonBChapters)
            );

            Serie serieA = new Serie(UUID.randomUUID().toString(),"Serie A", "terror", serieASeasons);
            Serie serieB = new Serie(UUID.randomUUID().toString(),"Serie B", "comedia", serieBSeasons);
            serieRepository.save(serieA);
            serieRepository.save(serieB);

        }
    }


