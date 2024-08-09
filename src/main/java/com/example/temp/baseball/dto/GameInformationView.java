package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Game;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameInformationView {
    private long id;
    private TeamInformation awayTeam;
    private TeamInformation homeTeam;
    private LocalDateTime startDate;
    private String stadium;

    public GameInformationView(Game game) {
        this.id = game.getId();
        this.awayTeam = new TeamInformation(game.getAwayTeam());
        this.homeTeam = new TeamInformation(game.getHomeTeam());
        this.startDate = game.getStartDate();
        this.stadium = game.getHomeTeam().getName();
    }
}
