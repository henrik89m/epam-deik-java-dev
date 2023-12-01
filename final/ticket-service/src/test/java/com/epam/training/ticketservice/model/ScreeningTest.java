package com.epam.training.ticketservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScreeningTest {

    @Test
    void isOverlappingShouldReturnFalse() {
        Movie movie = new Movie("Movie 1", "Genre", 120);
        Room room = new Room();
        LocalDateTime startTime = LocalDateTime.now();

        Screening screening = new Screening(movie, room, startTime);

        LocalDateTime otherStartTime = startTime.plusMinutes(movie.getLength() + 30);
        Screening nonOverlappingScreening = new Screening(new Movie("Movie 2", "Genre", 90), room, otherStartTime);

        boolean result = screening.isOverlapping(nonOverlappingScreening, 15);

        assertFalse(result, "Should not overlap");
    }

    @Test
    void isOverlappingOverlappingScreeningsWithNoBreakTimeShouldReturnTrue() {
        Movie movie = new Movie("Movie 1", "Genre", 120);
        Room room = new Room();
        LocalDateTime startTime = LocalDateTime.now();

        Screening screening = new Screening(movie, room, startTime);

        LocalDateTime otherStartTime = startTime.plusMinutes(movie.getLength() - 30);
        Screening overlappingScreening = new Screening(new Movie("Movie 2", "Genre", 90), room, otherStartTime);

        boolean result = screening.isOverlapping(overlappingScreening, 0);

        assertTrue(result, "Should overlap");
    }

    @Test
    void getId_ShouldReturnNull() {
        Screening screening = new Screening();

        Long id = screening.getId();

        assertNull(id, "Id should be null");
    }

    @Test
    void setId() {
        Screening screening = new Screening();
        Long id = 1L;

        screening.setId(id);

        assertEquals(id, screening.getId(), "Id should be set");
    }

    @Test
    void testEquals_IdenticalScreenings_ShouldReturnTrue() {
        Movie movie = new Movie("Movie 1", "Genre", 120);
        Room room = new Room();
        LocalDateTime startTime = LocalDateTime.now();

        Screening screening1 = new Screening(movie, room, startTime);
        Screening screening2 = new Screening(movie, room, startTime);

        boolean result = screening1.equals(screening2);

        assertTrue(result, "Screenings should be equal");
    }

    @Test
    void testEqualsDifferentScreeningsShouldReturnFalse() {
        Movie movie1 = new Movie("Movie 1", "Genre", 120);
        Movie movie2 = new Movie("Movie 2", "Genre", 90);
        Room room1 = new Room();
        Room room2 = new Room();
        LocalDateTime startTime1 = LocalDateTime.now();
        LocalDateTime startTime2 = LocalDateTime.now().plusHours(1);

        Screening screening1 = new Screening(movie1, room1, startTime1);
        Screening screening2 = new Screening(movie2, room2, startTime2);

        boolean result = screening1.equals(screening2);

        assertFalse(result, "Screenings should not be equal");
    }

    @Test
    void testCanEqual() {
        Movie movie = new Movie("Movie 1", "Genre", 120);
        Room room = new Room();
        LocalDateTime startTime = LocalDateTime.now();

        Screening screening1 = new Screening(movie, room, startTime);
        Screening screening2 = new Screening(movie, room, startTime);

        boolean result = screening1.canEqual(screening2);

        assertTrue(result, "Should be able to equal");
    }

    @Test
    void testHashCode() {
        Movie movie = new Movie("Movie 1", "Genre", 120);
        Room room = new Room();
        LocalDateTime startTime = LocalDateTime.now();

        Screening screening1 = new Screening(movie, room, startTime);
        Screening screening2 = new Screening(movie, room, startTime);

        int hashCode1 = screening1.hashCode();
        int hashCode2 = screening2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes should be equal");
    }

    @Test
    void testToString() {
        Movie movie = new Movie("Movie 1", "Genre", 120);
        Room room = new Room();
        LocalDateTime startTime = LocalDateTime.now();

        Screening screening = new Screening(movie, room, startTime);

        String result = screening.toString();

        assertNotNull(result, "ToString result should not be null");
    }


}
