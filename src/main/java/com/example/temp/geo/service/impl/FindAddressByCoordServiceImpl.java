package com.example.temp.geo.service.impl;

import com.example.temp.common.entity.Address;
import com.example.temp.geo.dto.FindAddressResponse;
import com.example.temp.geo.service.FindAddressByCoordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAddressByCoordServiceImpl implements FindAddressByCoordService {
    private final Coord2AddressClient coord2AddressClient;

    @Override
    public FindAddressResponse doService(String x, String y) {
        Address address = coord2AddressClient.trans(x, y);
        return FindAddressResponse.of(address);
    }
}
