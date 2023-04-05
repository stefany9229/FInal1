package com.dh.catalogservice;

import com.dh.catalogservice.api.repository.IMovieRepository;
import com.dh.catalogservice.api.repository.ISerieRepository;
import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.IProduct;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CatalogServiceApplication {

    public static void main(String[] args) {


        ApplicationContext context=SpringApplication.run(CatalogServiceApplication.class, args);
 /*
       List<Movie> movies =  context.getBean(IMovieRepository.class).findAllByGenre("terror");
        List<Serie> series =  context.getBean(ISerieRepository.class).findAllByGenre("terror");


        List<IProduct> productList = new ArrayList<IProduct>();
        for (Movie movie : movies) {
            productList.add((IProduct) movie);
        }

        for (Serie serie : series) {
            productList.add((IProduct) serie);
        }
        System.out.println(productList);
        */
    }

}
