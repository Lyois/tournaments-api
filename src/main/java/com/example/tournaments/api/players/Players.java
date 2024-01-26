package com.example.tournaments.api.players;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "players")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonPropertyOrder({"id", "team_id", "lane", "name", "kda", "kp", "vspm", "dmg", "gold"})
public class Players {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id")
    private String teamId;
    private String lane;
    private String name;
    private double kda;
    private double kp;
    private double vspm;
    private double dmg;
    private double gold;

    public Players(PlayersRequestDTO data){
        this.teamId = data.teamId();
        this.lane = data.lane();
        this.name = data.name();
        this.kda = data.kda();
        this.kp = data.kp();
        this.vspm = data.vspm();
        this.dmg = data.dmg();
        this.gold = data.gold();
    }


}
