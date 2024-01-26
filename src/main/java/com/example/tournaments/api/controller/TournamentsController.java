package com.example.tournaments.api.controller;

import com.example.tournaments.api.tournaments.TournamentsRepository;
import com.example.tournaments.api.tournaments.TournamentsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tournaments")
public class TournamentsController {

    @Autowired
    private TournamentsRepository tournamentsRepository;

    @GetMapping
    public List<TournamentsResponseDTO> getAll(){
        return tournamentsRepository.findAll().stream().map(TournamentsResponseDTO::new).toList();
    }
}