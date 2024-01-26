package com.example.tournaments.api.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersService {

    @Autowired
    private PlayersRepository playersRepository;

    public void save(List<PlayersRequestDTO> data){
        for (PlayersRequestDTO data2 : data){
            Players playersData = new Players(data2);
            playersRepository.save(playersData);
        }
    }
}
