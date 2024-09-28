package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.*;
import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.service.FindAllSchedulesDuringThisWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllSchedulesDuringThisWeekServiceImpl implements FindAllSchedulesDuringThisWeekService {
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public GameScheduleResponse doService(Team team, int year) {
        Season season = seasonRepository.findByYearOrElseThrow(year);

        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusDays(1);
        LocalDate endOfWeek = startOfWeek.plusDays(7);

        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = endOfWeek.atStartOfDay();

        List<Game> found = gameRepository
                .findAllByTeamAndCurrentWeekInYear(season, team, startOfWeekDateTime, endOfWeekDateTime);
        return new GameScheduleResponse(found);
    }
}
