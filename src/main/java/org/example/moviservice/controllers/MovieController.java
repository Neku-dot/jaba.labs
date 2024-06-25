package org.example.moviservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.moviservice.dto.MovieDto;
import org.example.moviservice.services.MovieService;
import org.example.moviservice.services.RequestCounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;
    private final RequestCounterService requestCounterService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        log.info("Trying GET all movies");
        log.info("{}", requestCounterService.incrementAndGet());
        List<MovieDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        log.info("Trying GET movie by id");
        log.info("{}", requestCounterService.incrementAndGet());
        MovieDto movieDto = movieService.getMovieById(id);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping("/after/{year}")
    public ResponseEntity<List<MovieDto>> getMoviesReleasedAfterYear(@PathVariable int year) {
        log.info("Trying GET movies released after year");
        log.info("{}", requestCounterService.incrementAndGet());
        List<MovieDto> movies = movieService.getMoviesReleasedAfterYear(year);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        log.info("Trying POST movie");
        log.info("{}", requestCounterService.incrementAndGet());
        MovieDto createdMovie = movieService.createMovie(movieDto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PostMapping("/bulk-create")
    public ResponseEntity<String> createMovieBulk(@RequestBody List<MovieDto> movieDtoList) {
        movieService.createMovieBulk(movieDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bulk movie creation successful");
    }

    @PostMapping("/bulk-update")
    public ResponseEntity<String> updateCipherBulk(@RequestBody List<MovieDto> movieDtoList) {
        movieService.updateCipherBulk(movieDtoList);
        return ResponseEntity.status(HttpStatus.OK).body("Bulk movie update successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        log.info("Trying PUT movie");
        log.info("{}", requestCounterService.incrementAndGet());
        MovieDto updatedMovie = movieService.updateMovie(id, movieDto);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        log.info("Trying DEL movie");
        log.info("{}", requestCounterService.incrementAndGet());
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}