package com.example.tournaments.api.controller;

import com.example.tournaments.api.teams.Teams;
import com.example.tournaments.api.teams.TeamsRepository;
import com.example.tournaments.api.teams.TeamsResponseDTO;
import com.example.tournaments.api.tournaments.TournamentsRepository;
import com.example.tournaments.api.tournaments.TournamentsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("teams")
public class TeamsController {

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    TournamentsRepository tournamentsRepository;

    @GetMapping("/{tournamentId}")
    public ResponseEntity<List<TeamsResponseDTO>> findTournamentTeams(@PathVariable String tournamentId){
        List<TeamsResponseDTO> teams = teamsRepository.findByCamp(tournamentId.toUpperCase()).stream().map(TeamsResponseDTO::new).toList();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{tournamentId}/{teamTag}")
    public ResponseEntity<TeamsResponseDTO> findTeam(@PathVariable String tournamentId, @PathVariable String teamTag){
        var team = teamsRepository.findByCampAndTag(tournamentId.toUpperCase(), teamTag.toUpperCase());
        return ResponseEntity.ok(new TeamsResponseDTO(team));
    }
}
