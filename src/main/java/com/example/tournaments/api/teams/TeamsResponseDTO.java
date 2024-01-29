package com.example.tournaments.api.teams;

public record TeamsResponseDTO(Long id, String camp, String teamId, String tag, String name, String region, int wins,
                               int loses, double winPercent, double gameDuration, String urlIcon) {

    public TeamsResponseDTO(Teams teams) {
        this(teams.getId(), teams.getCamp(), teams.getTeamId(), teams.getTag(), teams.getName(), teams.getRegion(),
                teams.getWins(), teams.getLoses(), teams.getWinPercent(), teams.getGameDuration(), teams.getUrlIcon());
    }
}
