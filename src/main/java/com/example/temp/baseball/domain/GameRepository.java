package com.example.temp.baseball.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends Repository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE g.season = :season AND (g.homeTeam = :team OR g.awayTeam = :team) AND g.startDate >= :startOfWeek AND g.startDate < :endOfWeek")
    List<Game> findAllByTeamAndCurrentWeekInYear(@Param("season") Season season,
                                                 @Param("team") Team team,
                                                 @Param("startOfWeek") LocalDateTime startOfWeek,
                                                 @Param("endOfWeek") LocalDateTime endOfWeek);

    Game save(Game saveGame);

    Optional<Game> findById(Long gameId);

    default Game findByIdOrElseThrow(Long gameId) {
        return findById(gameId).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "Game not found"));
    }
}
