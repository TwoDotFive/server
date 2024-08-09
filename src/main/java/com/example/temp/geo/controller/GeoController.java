package com.example.temp.geo.controller;

import com.example.temp.geo.dto.FindAddressResponse;
import com.example.temp.geo.service.FindAddressByCoordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
@RequiredArgsConstructor
public class GeoController {
    private final FindAddressByCoordService findAddressByCoordService;

    @GetMapping("/coord2address")
    public ResponseEntity<FindAddressResponse> trans(
            @RequestParam("x") String x,
            @RequestParam("y") String y
    ) {
        FindAddressResponse result = findAddressByCoordService.doService(x, y);
        return ResponseEntity.ok(result);
    }
}
