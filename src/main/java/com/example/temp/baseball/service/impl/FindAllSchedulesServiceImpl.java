package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.GameRepository;
import com.example.temp.baseball.domain.Season;
import com.example.temp.baseball.domain.SeasonRepository;
import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.dto.GameSchedulesRequest;
import com.example.temp.baseball.service.FindAllSchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllSchedulesServiceImpl implements FindAllSchedulesService {
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public List<GameScheduleResponse> doService(Team team, int year) {
        Season season = seasonRepository.findByYearOrElseThrow(year);

        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(7); // 다음 주 일요일 (exclusive)

        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = endOfWeek.atStartOfDay();

        return gameRepository.findAllByTeamAndCurrentWeekInYear(season, team, startOfWeekDateTime, endOfWeekDateTime)
                .stream()
                .map(GameScheduleResponse::new)
                .collect(Collectors.toList());
    }
}
