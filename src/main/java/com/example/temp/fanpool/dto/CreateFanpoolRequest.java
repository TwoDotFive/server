package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.value.FanpoolType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CreateFanpoolRequest {
    private LocalDateTime time;
    private Long gameId;
    private Integer numberOfPeople;
    private String memo;
    private FanpoolType fanpoolType;
    private Double x;
    private Double y;
}
