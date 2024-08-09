package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.Season;
import com.example.temp.baseball.domain.SeasonRepository;
import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.domain.TeamRepository;
import com.example.temp.baseball.dto.TeamInformation;
import com.example.temp.baseball.service.FindAllTeamsInSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindAllTeamsInSeasonServiceImpl implements FindAllTeamsInSeasonService {
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public List<TeamInformation> doService(int year) {
        Optional<Season> found = seasonRepository.findByYear(year);
        if (found.isEmpty()) {
            return List.of();
        }

        List<Team> teams = teamRepository.findBySeason(found.get());

        return teams.stream()
                .map(TeamInformation::new)
                .toList();
    }
}
