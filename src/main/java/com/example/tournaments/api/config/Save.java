package com.example.tournaments.api.config;

import com.example.tournaments.api.players.PlayersRepository;
import com.example.tournaments.api.players.PlayersRequestDTO;
import com.example.tournaments.api.players.PlayersResponseDTO;
import com.example.tournaments.api.players.PlayersService;
import com.example.tournaments.api.tournaments.TournamentsRepository;
import com.example.tournaments.api.tournaments.TournamentsRequestDTO;
import com.example.tournaments.api.tournaments.TournamentsResponseDTO;
import com.example.tournaments.api.tournaments.TournamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Save {
    @Autowired
    private Scraping scraping;

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private TournamentsRepository tournamentsRepository;

    @Autowired
    TournamentsService tournamentsService;

    @Autowired
    PlayersService playersService;

    @Bean
//    @Scheduled(fixedDelay = 10000)
    public void saveDataBase() throws Exception {
        List<PlayersResponseDTO> listDB = playersRepository.findAll().stream().map(PlayersResponseDTO::new).toList();
        List<TournamentsResponseDTO> listDBTournaments = tournamentsRepository.findAll().stream().map(TournamentsResponseDTO::new).toList();

        if (listDBTournaments.isEmpty()){
            for (var listTournament : scraping.listTournaments()) {
                List<TournamentsRequestDTO> listScrapingTournament = scraping.scrapingTournamentsData(listTournament);
                tournamentsService.save(listScrapingTournament);
            }

        }
        if (listDB.isEmpty()) {
            for (String listTournament : scraping.listTournaments()) {
                for (var listTest : scraping.scrapingTeams(listTournament)) {
                    List<PlayersRequestDTO> listScraping = scraping.scrapingPlayers(listTest);
                    playersService.save(listScraping);
                }
            }
        }

    }
}
