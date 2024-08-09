package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.GameSchedulesRequest;

import java.util.List;

public interface CreateGameSchedulesService {
    void doService(boolean isAdmin, List<GameSchedulesRequest> request);
}
