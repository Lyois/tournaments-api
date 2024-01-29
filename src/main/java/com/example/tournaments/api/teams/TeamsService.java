package com.example.tournaments.api.teams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsService {

    @Autowired
    TeamsRepository teamsRepository;

    public void  save(List<TeamsRequestDTO> data){
        for (TeamsRequestDTO data2 : data){
            Teams teamsData = new Teams(data2);
            teamsRepository.save(teamsData);
        }
    }
}
