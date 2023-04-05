package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.api.repository.ISerieRepository;
import com.dh.catalogservice.domain.model.IProduct;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatalogService implements ICatalogService {


    private IMovieRepository movieRepository;
    private ISerieRepository serieRepository;

    public CatalogService(IMovieRepository movieRepository, ISerieRepository serieRepository) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }

    public List<IProduct> listarPorGenero(String genre) {
        List<Movie> movies = movieRepository.findAllByGenre(genre);
        List<Serie> series = serieRepository.findAllByGenre(genre);
        List<IProduct> productList = new ArrayList<IProduct>();
        for (Movie movie : movies) {
            productList.add((IProduct) movie);
        }

        for (Serie serie : series) {
            productList.add((IProduct) serie);
        }

        return productList;
    }
}
