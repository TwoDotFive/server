package com.example.temp.fanpool.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.fanpool.dto.FanpoolInformationView;
import com.example.temp.fanpool.service.CreateFanpoolService;
import com.example.temp.fanpool.service.FindFanpoolByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fanpool")
@RequiredArgsConstructor
public class FanpoolController {
    private final CreateFanpoolService createFanpoolService;
    private final FindFanpoolByIdService findFanpoolByIdService;

    @GetMapping("/{fanpoolId}")
    public ResponseEntity<FanpoolInformationView> getFanpool(
            @RequestParam("fanpoolId") long fanpoolId
    ) {
        FanpoolInformationView result = findFanpoolByIdService.doService(fanpoolId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<FanpoolInformationView> save(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateFanpoolRequest request
    ) {
        FanpoolInformationView result = createFanpoolService.doService(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
