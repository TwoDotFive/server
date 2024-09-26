package com.example.temp.tour.service.impl;

import com.example.temp.tour.dto.FindAccommodationsDetailsResponse;
import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsResponse;
import com.example.temp.tour.service.FindTourInformationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class FindAccommodationDetailsServiceImpl implements FindTourInformationDetailsService {
    private final TourDetailsClients tourDetailsClients;

    @Override
    public FindTourInformationDetailsResponse doService(FindTourInformationDetailsCommand command) {
        WebClient.ResponseSpec responseSpec = tourDetailsClients.doService(command);

        FindAccommodationsDetailsResponse result = responseSpec.bodyToMono(FindAccommodationsDetailsResponse.class).block();
        return result.trans();
    }
}