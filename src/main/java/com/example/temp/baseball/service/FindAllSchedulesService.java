package com.example.temp.baseball.service;

import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.dto.GameSchedulesRequest;

import java.util.List;

public interface FindAllSchedulesService {
    List<GameScheduleResponse> doService(Team team, int year);
}
