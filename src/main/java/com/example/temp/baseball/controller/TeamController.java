package com.example.temp.baseball.controller;

import com.example.temp.baseball.dto.TeamInformation;
import com.example.temp.baseball.service.FindAllTeamsInSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/baseball")
@RequiredArgsConstructor
public class TeamController {
    private final FindAllTeamsInSeasonService findAllTeamsInSeasonService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamInformation>> find(
            @RequestParam(name = "year", required = true) int year
    ) {
        List<TeamInformation> result = findAllTeamsInSeasonService.doService(year);
        return ResponseEntity.ok(result);
    }
}
