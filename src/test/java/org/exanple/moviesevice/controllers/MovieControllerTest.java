package org.exanple.moviesevice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.moviservice.controllers.MovieController;
import org.example.moviservice.dto.MovieDto;
import org.example.moviservice.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieControllerTest {

    private MovieController movieController;
    private MovieService movieService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        movieService = mock(MovieService.class);
        movieController = new MovieController(movieService);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllMovies() {
        // Arrange
        List<MovieDto> mockMovies = List.of(new MovieDto(), new MovieDto());
        when(movieService.getAllMovies()).thenReturn(mockMovies);

        // Act
        ResponseEntity<List<MovieDto>> responseEntity = movieController.getAllMovies();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockMovies, responseEntity.getBody());
    }

    @Test
    void testGetMovieById() {
        // Arrange
        Long id = 1L;
        MovieDto mockMovie = new MovieDto();
        when(movieService.getMovieById(id)).thenReturn(mockMovie);

        // Act
        ResponseEntity<MovieDto> responseEntity = movieController.getMovieById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockMovie, responseEntity.getBody());
    }

    @Test
    void testGetMoviesReleasedAfterYear() {
        // Arrange
        int year = 2022;
        List<MovieDto> mockMovies = List.of(new MovieDto(), new MovieDto());
        when(movieService.getMoviesReleasedAfterYear(year)).thenReturn(mockMovies);

        // Act
        ResponseEntity<List<MovieDto>> responseEntity = movieController.getMoviesReleasedAfterYear(year);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockMovies, responseEntity.getBody());
    }

    @Test
    void testCreateMovie() throws Exception {
        // Arrange
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Test Movie");
        String requestBody = objectMapper.writeValueAsString(movieDto);

        MovieDto mockCreatedMovie = new MovieDto();
        when(movieService.createMovie(movieDto)).thenReturn(mockCreatedMovie);

        // Act
        ResponseEntity<MovieDto> responseEntity = movieController.createMovie(movieDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockCreatedMovie, responseEntity.getBody());
    }

    @Test
    void testUpdateMovie() throws Exception {
        // Arrange
        Long id = 1L;
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Updated Movie");
        String requestBody = objectMapper.writeValueAsString(movieDto);

        MovieDto mockUpdatedMovie = new MovieDto();
        when(movieService.updateMovie(id, movieDto)).thenReturn(mockUpdatedMovie);

        // Act
        ResponseEntity<MovieDto> responseEntity = movieController.updateMovie(id, movieDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUpdatedMovie, responseEntity.getBody());
    }

    @Test
    void testDeleteMovie() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> responseEntity = movieController.deleteMovie(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

