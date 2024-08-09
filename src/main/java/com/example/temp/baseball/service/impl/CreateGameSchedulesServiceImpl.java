package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.*;
import com.example.temp.baseball.dto.GameSchedulesRequest;
import com.example.temp.baseball.service.CreateGameSchedulesService;
import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateGameSchedulesServiceImpl implements CreateGameSchedulesService {
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public void doService(boolean isAdmin, List<GameSchedulesRequest> gameSchedulesRequest) {
        if (!isAdmin) {
            throw new CustomException(HttpStatus.FORBIDDEN, "User is not allowed to do this");
        }

        for (GameSchedulesRequest req : gameSchedulesRequest) {
            Season season = seasonRepository.findByYearOrElseThrow(req.getSeason());
            Team homeTeam = teamRepository.findByIdOrElseThrow(req.getHomeTeam());
            Team awayTeam = teamRepository.findByIdOrElseThrow(req.getAwayTeam());

            Game saveGame = Game.builder()
                    .season(season)
                    .homeTeam(homeTeam)
                    .awayTeam(awayTeam)
                    .startDate(req.getStartDate())
                    .build();
            gameRepository.save(saveGame);
        }

    }
}
