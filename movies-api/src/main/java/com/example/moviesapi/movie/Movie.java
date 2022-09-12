package com.example.moviesapi.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Movie")
public class Movie {
        @Id
        private String id;
        private String title;
        private String release_date;
        private String poster_path;
        private String tagline;
        private float vote_average;
        private String overview;
}
