package com.example.tournaments.api.tournaments;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tournaments")
@Entity(name = "tournaments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonPropertyOrder({"id", "tournamentId", "name", "season", "iconUrl"})
public class Tournaments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tournament_id")
    private String tournamentId;
    private String name;
    private String season;
    @Column(name = "icon_url")
    private String iconUrl;

    public Tournaments(TournamentsRequestDTO data){
        this.tournamentId = data.tournamentId();
        this.name = data.name();
        this.season = data.season();
        this.iconUrl = data.iconUrl();
    }
}
