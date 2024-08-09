package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Game;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class GameScheduleResponse {
    public long id;
    public LocalDate startDate;
    public LocalTime startTime;
    public TeamInformation home;
    public TeamInformation away;
    public Game.State state;

    public GameScheduleResponse(Game game) {
        this.id = game.getId();
        this.startDate = game.getStartDate().toLocalDate();
        this.startTime = game.getStartDate().toLocalTime();
        this.home = new TeamInformation(game.getHomeTeam());
        this.away = new TeamInformation(game.getAwayTeam());
        this.state = determineState(game.getStartDate());
    }

    private Game.State determineState(LocalDateTime startAt) {
        LocalDateTime now = LocalDateTime.now();

        // 평균 야구 경기 시간을 3시간 10분 Duration으로 정의
        Duration gameDuration = Duration.ofHours(3).plusMinutes(10);

        if (now.isBefore(startAt)) {
            return Game.State.BEFORE_START;
        }
        if (now.isAfter(startAt) && now.isBefore(startAt.plus(gameDuration))) {
            return Game.State.IN_PROGRESS;
        }
        return Game.State.TERMINATED;
    }
}
