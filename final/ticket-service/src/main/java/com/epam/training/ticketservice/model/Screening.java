package com.epam.training.ticketservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "screenings")
@NoArgsConstructor
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private LocalDateTime startTime;

    public Screening(Movie movie, Room room, LocalDateTime startTime) {
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
    }

    public boolean isOverlapping(Screening screening, int breakTime) {
        var start = getStartTime();
        var end = start.plusMinutes(movie.getLength());
        var screeningStart = screening.getStartTime();
        var screeningEnd = screeningStart.plusMinutes(screening.getMovie().getLength() + breakTime);

        boolean isOverlapp = !(end.isBefore(screeningStart) || start.isAfter(screeningEnd));

        return isOverlapp;
    }

}
