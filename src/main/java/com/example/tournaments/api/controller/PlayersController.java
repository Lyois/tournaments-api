package com.example.tournaments.api.controller;

import com.example.tournaments.api.players.Players;
import com.example.tournaments.api.players.PlayersRepository;
import com.example.tournaments.api.players.PlayersResponseDTO;
import com.example.tournaments.api.teams.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("players")
public class PlayersController {

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @GetMapping
    public ResponseEntity<List<Players>> listar() throws IOException{
        List<Players> list = playersRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{tag}")
    public ResponseEntity<List<PlayersResponseDTO>> findTeamPlayers(@PathVariable String tag){
        String teamId = teamsRepository.findByTag(tag.toUpperCase()).getTeamId();
        System.out.println(teamId);
        List<PlayersResponseDTO> players = playersRepository.findByTeamId(teamId).stream().map(PlayersResponseDTO::new).toList();
        return ResponseEntity.ok(players);
    }
}
