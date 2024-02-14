package com.example.tournaments.api.players;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayersRepository extends JpaRepository<Players, Long> {

    Players findByName(String name);

    List<Players> findByTeamId(String teamId);
}
