package com.example.temp.geo.service;

import com.example.temp.geo.dto.FindAddressResponse;

public interface FindAddressByCoordService {
    FindAddressResponse doService(String x, String y);
}
