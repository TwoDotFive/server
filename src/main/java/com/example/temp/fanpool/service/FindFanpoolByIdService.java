package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FanpoolInformationView;

public interface FindFanpoolByIdService {
    FanpoolInformationView doService(long id);
}
