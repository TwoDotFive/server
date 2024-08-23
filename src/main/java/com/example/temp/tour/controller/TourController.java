package com.example.temp.tour.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.tour.dto.FindTourInformationByLocationCommand;
import com.example.temp.tour.dto.FindTourInformationResponse;
import com.example.temp.tour.dto.RegisterTourLogRequest;
import com.example.temp.tour.dto.TourLogView;
import com.example.temp.tour.service.DeleteTourLogService;
import com.example.temp.tour.service.FindTourInformationByLocationService;
import com.example.temp.tour.service.FindTourLogService;
import com.example.temp.tour.service.RegisterTourLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController {

    private final FindTourLogService findTourLogService;
    private final DeleteTourLogService deleteTourLogService;
    private final RegisterTourLogService registerTourLogService;
    private final FindTourInformationByLocationService findTourInformationByLocationService;

    @GetMapping("/info")
    public ResponseEntity<FindTourInformationResponse> findTourInformation(
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "pageNumber") int pageNumber,
            @RequestParam(name = "x") String x,
            @RequestParam(name = "y") String y,
            @RequestParam(name = "radius") String radius,
            @RequestParam(name = "contentType") String contentTypeId
    ) {
        FindTourInformationByLocationCommand command = new FindTourInformationByLocationCommand(
                pageSize, pageNumber, x, y, radius, contentTypeId);
        FindTourInformationResponse response = findTourInformationByLocationService.doService(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/log")
    public ResponseEntity<Long> registerTourLog(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody RegisterTourLogRequest request
    ) {
        Long tourLogId = registerTourLogService.doService(userDetails.getId(), request);
        return ResponseEntity.ok(tourLogId);
    }

    @GetMapping("/log")
    public ResponseEntity<TourLogView> findTourLog(@RequestParam(name = "id") Long tourLogId) {
        TourLogView response = findTourLogService.doService(tourLogId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/log")
    public ResponseEntity<Void> deleteTourLog(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(name = "id") Long tourLogId
    ) {
        deleteTourLogService.doService(customUserDetails.getId(), tourLogId);
        return ResponseEntity.ok().build();
    }

}
