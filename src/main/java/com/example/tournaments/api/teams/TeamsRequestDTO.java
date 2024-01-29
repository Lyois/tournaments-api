package com.example.tournaments.api.teams;

import java.awt.*;

public record TeamsRequestDTO(String camp, String teamId, String tag, String name, String region, int wins, int loses, double winPercent, double gameDuration, String urlIcon) {
}
