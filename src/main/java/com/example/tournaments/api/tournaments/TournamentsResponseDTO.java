package com.example.tournaments.api.tournaments;

public record TournamentsResponseDTO(Long id, String tournamentId, String name, String region, String iconUrl) {

    public TournamentsResponseDTO(Tournaments tournaments) {
        this(tournaments.getId(), tournaments.getTournamentId(), tournaments.getName(), tournaments.getSeason(), tournaments.getIconUrl());
    }
}