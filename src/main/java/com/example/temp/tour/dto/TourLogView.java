package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourLog;

import java.util.List;

public record TourLogView(
        String title,
        String stadiumName,
        TourLogUserProfileView user,
        List<TourScheduleView> schedules
) {

    public static TourLogView of(TourLog tourLog) {
        List<TourScheduleView> scheduleViews = tourLog.getSchedules()
                .stream()
                .map(TourScheduleView::of)
                .toList();

        return new TourLogView(
                tourLog.getTitle(),
                tourLog.getStadium().getShortenName(),
                TourLogUserProfileView.of(tourLog.getUser()),
                scheduleViews
        );
    }
}