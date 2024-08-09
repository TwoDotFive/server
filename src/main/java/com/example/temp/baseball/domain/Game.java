package com.example.temp.baseball.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Game {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team homeTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team awayTeam;
    private LocalDateTime startDate;
    @Enumerated(EnumType.STRING)
    private State state;

    @RequiredArgsConstructor
    public enum State {
        BEFORE_START("경기 전"),
        IN_PROGRESS("경기중"),
        TERMINATED("경기종료");

        private final String kor;
    }

    @Builder
    public Game(Season season, Team homeTeam, Team awayTeam, LocalDateTime startDate) {
        this.id = IdUtil.create();
        this.season = season;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startDate = startDate;
        this.state = State.BEFORE_START;
    }
}
