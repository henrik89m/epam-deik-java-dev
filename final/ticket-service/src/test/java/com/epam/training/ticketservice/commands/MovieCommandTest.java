package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.dto.MovieDto;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieCommandTest {

    @InjectMocks
    private MovieCommand movieCommand;

    @Mock
    private MovieService movieService;

    @Test
    void createMovie_Success() {
        String title = "Inception";
        String genre = "Sci-Fi";
        int length = 150;

        when(movieService.createMovie(title, genre, length)).thenReturn(Optional.of(new MovieDto(title, genre, length)));

        String result = movieCommand.createMovie(title, genre, length);

        assertEquals("Movie created successfully", result);
        verify(movieService, times(1)).createMovie(title, genre, length);
    }

    @Test
    void updateMovie_Success() {
        String title = "Inception";
        String genre = "Sci-Fi";
        int length = 150;

        when(movieService.updateMovie(title, genre, length)).thenReturn(Optional.of(new MovieDto(title,genre, length)));

        String result = movieCommand.updateMovie(title, genre, length);

        assertEquals("Movie updated successfully", result);
        verify(movieService, times(1)).updateMovie(title, genre, length);
    }

    @Test
    void deleteMovie_Success() {
        String title = "Inception";

        when(movieService.deleteMovie(title)).thenReturn(null);

        String result = movieCommand.deleteMovie(title);

        assertEquals("Movie deleted successfully", result);
        verify(movieService, times(1)).deleteMovie(title);
    }


    @Test
    void listMovies_EmptyList() {
        when(movieService.listMovies()).thenReturn(Collections.emptyList());

        assertDoesNotThrow(() -> movieCommand.listMovies());
    }

    @Test
    void listMovies_NonEmptyList() {
        List<MovieDto> movies = List.of(
                new MovieDto("Inception", "Sci-Fi", 150),
                new MovieDto("The Matrix", "Action", 136)
        );

        when(movieService.listMovies()).thenReturn(movies);

        assertDoesNotThrow(() -> movieCommand.listMovies());
    }
}
