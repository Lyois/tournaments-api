package com.example.tournaments.api.controller;

import com.example.tournaments.api.players.Players;
import com.example.tournaments.api.players.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("web")
public class PlayersController {

    @Autowired
    private PlayersRepository playersService;

    @GetMapping
    public ResponseEntity<List<Players>> listar() throws IOException{
        List<Players> list = playersService.findAll();
        return ResponseEntity.ok(list);
    }
}
