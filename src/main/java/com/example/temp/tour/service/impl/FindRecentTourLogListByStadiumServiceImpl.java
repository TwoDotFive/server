package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.domain.TourLogStadiumRepository;
import com.example.temp.tour.dto.TourLogPreview;
import com.example.temp.tour.service.FindRecentTourLogListByStadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRecentTourLogListByStadiumServiceImpl implements FindRecentTourLogListByStadiumService {

    private final TourLogRepository tourLogRepository;
    private final TourLogStadiumRepository tourLogStadiumRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TourLogPreview> doService(long stadiumId, long lastTourLogId, int pageSize) {
        if (!tourLogStadiumRepository.existsById(stadiumId)) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Invalid Stadium Id");
        }
        return tourLogRepository.findRecentTourLogListByStadium(stadiumId, lastTourLogId, pageSize)
                .stream()
                .map(TourLogPreview::build)
                .toList();
    }
}
