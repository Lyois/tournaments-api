package com.example.tournaments.api.tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentsService {

    @Autowired
    TournamentsRepository tournamentsRepository;

    public void save(List<TournamentsRequestDTO> data){
        for (TournamentsRequestDTO data2 : data) {
            Tournaments tournamentsData = new Tournaments(data2);
            tournamentsRepository.save(tournamentsData);
        }
    }
}
