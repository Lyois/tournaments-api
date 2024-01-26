package com.example.tournaments.api.players;

public record PlayersRequestDTO(String teamId, String lane, String name, double kda, double kp, double vspm,
                                double dmg, double gold) {
}
