package com.example.temp.tour.dto;

import java.util.List;

public record RegisterTourLogRequest(
        String title,
        String image,
        String stadiumId,
        List<TourScheduleDto> schedules
) {
}
