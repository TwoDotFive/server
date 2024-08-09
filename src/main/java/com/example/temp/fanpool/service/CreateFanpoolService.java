package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.fanpool.dto.FanpoolInformationView;

public interface CreateFanpoolService {
    FanpoolInformationView doService(long userId, CreateFanpoolRequest request);
}
