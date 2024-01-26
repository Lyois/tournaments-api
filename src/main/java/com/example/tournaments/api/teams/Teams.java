package com.example.tournaments.api.teams;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "players")
@Entity
@JsonPropertyOrder({"id", "camp", "teamId", "name", "region", "wins", "lose", "winPercent", "gameDuration"})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String camp;
    @Column(name = "team_id")
    private String teamId;
    private String name;
    private String region;
    @Column(name = "win_percent")
    private double winPercent;
    @Column(name = "game_duration")
    private double gameDuration;
    private String url_icon;
}
