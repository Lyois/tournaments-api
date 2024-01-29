package com.example.tournaments.api.teams;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "teams")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonPropertyOrder({"id", "tournament_id", "teamId", "tag", "name", "region", "wins", "loses", "winPercent", "gameDuration", "url_icon"})
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_id")
    private String camp;
    @Column(name = "team_id")
    private String teamId;
    private String tag;
    private String name;
    private String region;
    private int wins;
    private int loses;
    @Column(name = "win_percent")
    private double winPercent;
    @Column(name = "game_duration")
    private double gameDuration;
    @Column(name = "url_icon")
    private String urlIcon;

    public Teams(TeamsRequestDTO data){
        this.camp = data.camp();
        this.teamId = data.teamId();
        this.tag = data.tag();
        this.name = data.name();
        this.region = data.region();
        this.wins = data.wins();
        this.loses = data.loses();
        this.winPercent = data.winPercent();
        this.gameDuration = data.gameDuration();
        this.urlIcon = data.urlIcon();
    }
}
