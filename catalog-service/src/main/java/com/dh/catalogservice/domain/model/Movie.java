package com.dh.catalogservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

    @Document(collection = "Movies")
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter

    public class Movie implements IProduct{
            @Id
            private Long id;
            private String name;
            private String genre;
            private String urlStream;

        @Override
        public String toString() {
            return "Movie{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", genre='" + genre + '\'' +
                    ", urlStream='" + urlStream + '\'' +
                    '}';
        }
    }
