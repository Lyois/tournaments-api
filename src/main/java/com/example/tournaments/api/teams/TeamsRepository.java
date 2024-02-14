package com.example.tournaments.api.teams;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
    Teams findByTag (String tag);

    List<Teams> findByCamp(String tournamentId);
}
