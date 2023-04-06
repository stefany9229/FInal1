package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.client.IMoviesServiceClient;
import com.dh.catalogservice.api.client.ISeriesServiceClient;
import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.api.repository.ISerieRepository;
import com.dh.catalogservice.domain.model.IProduct;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class CatalogService implements ICatalogService {


    private IMovieRepository movieRepository;
    private ISerieRepository serieRepository;

    private IMoviesServiceClient iMoviesServiceClient;

    private ISeriesServiceClient iSeriesServiceClient;

    public CatalogService(IMovieRepository movieRepository, ISerieRepository serieRepository,
                          IMoviesServiceClient iMoviesServiceClient, ISeriesServiceClient iSeriesServiceClient) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
        this.iMoviesServiceClient = iMoviesServiceClient;
        this.iSeriesServiceClient = iSeriesServiceClient;
    }
    @CircuitBreaker(name="subscription",fallbackMethod = "subsciptionFallbackMethod")
    @Retry(name = "subscription")
    public List<IProduct> listarPorGeneroOnLine(String genre) {
        List<Movie> movies = iMoviesServiceClient.getMovieByGenre(genre);
        List<Serie> series = iSeriesServiceClient.getSerieByGenre(genre);
        List<IProduct> productList = new ArrayList<IProduct>();
        for (Movie movie : movies) {
            productList.add((IProduct) movie);
        }

        for (Serie serie : series) {
            productList.add((IProduct) serie);
        }

        return productList;
    }

    public List<IProduct> subsciptionFallbackMethod(String genre,CallNotPermittedException exception) {
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

    /*private List<IProduct> subsciptionFallbackMethod(CallNotPermittedException exception){
        return  null;
    }*/


}
