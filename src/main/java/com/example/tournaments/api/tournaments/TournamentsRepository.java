package com.example.tournaments.api.tournaments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentsRepository extends JpaRepository<Tournaments, Long> {

    Tournaments findByTournamentId(String tournamentId);
}
