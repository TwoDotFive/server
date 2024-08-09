package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.TeamInformation;

import java.util.List;

public interface FindAllTeamsInSeasonService {
    List<TeamInformation> doService(int year);
}
