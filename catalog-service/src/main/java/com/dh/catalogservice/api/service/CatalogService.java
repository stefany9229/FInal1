package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.domain.model.IProduct;
import com.dh.catalogservice.domain.model.Movie;
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

    @Autowired
    private IMovieRepository movieRepository;



    public List<IProduct> listarPorGenero (String genre){
        List<Movie> movies = movieRepository.findAllByGenre(genre);
        List<IProduct> productList = new ArrayList<IProduct>();
        for (Movie movie : movies) {
            productList.add((IProduct) movie);
        }
        return productList;
    }
}
