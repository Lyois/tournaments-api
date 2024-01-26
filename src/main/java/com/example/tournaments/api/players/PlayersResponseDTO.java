package com.example.tournaments.api.players;

public record PlayersResponseDTO(Long id, String teamId, String lane, String name, double kda, double kp, double vspm,
                                 double dmg, double gold) {

    public PlayersResponseDTO(Players players) {
        this(players.getId(), players.getTeamId(), players.getLane(), players.getName(),
                players.getKda(), players.getKp(), players.getVspm(), players.getDmg(), players.getGold());
    }

}