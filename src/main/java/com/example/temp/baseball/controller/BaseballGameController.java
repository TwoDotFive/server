package com.example.temp.baseball.controller;

import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.dto.GameSchedulesRequest;
import com.example.temp.baseball.service.CreateGameSchedulesService;
import com.example.temp.baseball.service.FindAllSchedulesService;
import com.example.temp.common.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baseball-game")
public class BaseballGameController {

    private final FindAllSchedulesService findAllSchedulesService;
    private final CreateGameSchedulesService createGameSchedulesService;

    @GetMapping("/schedules")
    public ResponseEntity<List<GameScheduleResponse>> findAllSchedules(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestParam(name = "year", required = true) int year
    ) {
        List<GameScheduleResponse> result = findAllSchedulesService.doService(authenticatedUser.getFavoriteTeam(), year);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/schedules")
    public ResponseEntity<Void> save(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestBody List<GameSchedulesRequest> request
    ) {
        createGameSchedulesService.doService(authenticatedUser.isAdmin(), request);
        return null;
    }

}
