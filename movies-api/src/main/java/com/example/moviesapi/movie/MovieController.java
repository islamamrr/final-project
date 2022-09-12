package com.example.moviesapi.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    private MovieRepo repo;

    @RequestMapping("/")
    public ResponseEntity<Map<String, Object>> getMovies(@RequestParam(defaultValue = "0") int page) {
        List<Movie> movies;
        Pageable paging = PageRequest.of(page, 10);

        Page<Movie> pageMovies;

        pageMovies = repo.findAll(paging);
        movies = pageMovies.getContent();

        Map<String, Object> response = new HashMap<>();

        response.put("results", movies);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItems", pageMovies.getTotalElements());
        response.put("totalPages", pageMovies.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("/movie/{id}")
    public Optional<Movie> getMovie(@PathVariable String id) {
        return repo.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void addOrUpdateMovie(@RequestBody Movie movie) {
        repo.save(movie);
    }
}
