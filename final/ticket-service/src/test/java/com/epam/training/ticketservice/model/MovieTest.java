package com.epam.training.ticketservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieTest {

    @Test
    void setId() {
        Movie movie = new Movie("Inception", "sci-fi", 148);
        Long id = 1L;

        movie.setId(id);

        assertEquals(id, movie.getId(), "Id should be set");
    }

    @Test
    void testEquals_IdenticalMovies_ShouldReturnTrue() {
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("Inception", "Sci-Fi", 148);

        boolean result = movie1.equals(movie2);

        assertTrue(result, "Movies should be equal");
    }

    @Test
    void testEquals_DifferentMovies_ShouldReturnFalse() {
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("Interstellar", "Sci-Fi", 169);

        boolean result = movie1.equals(movie2);

        assertFalse(result, "Movies should not be equal");
    }

    @Test
    void testHashCode_IdenticalMovies_ShouldReturnEqualHashCodes() {
        Movie movie1 = new Movie("Inception", "sci-fi", 148);
        Movie movie2 = new Movie("Inception", "sci-fi", 148);

        int hashCode1 = movie1.hashCode();
        int hashCode2 = movie2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes equal");
    }

    @Test
    void testHashCode_DifferentMovies_ShouldReturnDifferentHashCodes() {
        Movie movie1 = new Movie("inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("Alien", "Sci-Fi", 169);

        int hashCode1 = movie1.hashCode();
        int hashCode2 = movie2.hashCode();

        assertNotEquals(hashCode1, hashCode2, "Hash codes not equal");
    }


}
