package com.example.tournaments.api.config;

import com.example.tournaments.api.players.PlayersRepository;
import com.example.tournaments.api.players.PlayersRequestDTO;
import com.example.tournaments.api.players.PlayersResponseDTO;
import com.example.tournaments.api.players.PlayersService;
import com.example.tournaments.api.teams.*;
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
    private TeamsRepository teamsRepository;

    @Autowired
    TournamentsService tournamentsService;

    @Autowired
    PlayersService playersService;

    @Autowired
    TeamsService teamsService;

    @Bean
//    @Scheduled(fixedDelay = 10000)
    public void saveDataBase() {
        List<PlayersResponseDTO> listDB = playersRepository.findAll().stream().map(PlayersResponseDTO::new).toList();
        List<TournamentsResponseDTO> listDBTournaments = tournamentsRepository.findAll().stream().map(TournamentsResponseDTO::new).toList();
        List<TeamsResponseDTO> listDBTeams = teamsRepository.findAll().stream().map(TeamsResponseDTO::new).toList();

        // Salvar os dados dos campeonatos no banco de dados.
        if (listDBTournaments.isEmpty()){
            for (var listTournaments : scraping.listTournaments()) {
                List<TournamentsRequestDTO> listScrapingTournament = scraping.scrapingTournamentsData(listTournaments);
                tournamentsService.save(listScrapingTournament);
            }

        }

        // Salvar os dados dos times no banco de dados.
        if (listDBTeams.isEmpty()){
            for (String listTeams : scraping.listTournaments()) {
                for (var team : scraping.scrapingTeams(listTeams)) {
                    List<TeamsRequestDTO> teamsData = scraping.scrapingTeamsData(team);
                    teamsService.save(teamsData);
                }
            }
        }
        // Salvar os dados dos jogadores no banco de dados.
        if (listDB.isEmpty()) {
            for (String listTeams : scraping.listTournaments()) {
                for (var team : scraping.scrapingTeams(listTeams)) {
                    List<PlayersRequestDTO> playersData = scraping.scrapingPlayers(team);
                    playersService.save(playersData);
                }
            }
        }

    }
}
