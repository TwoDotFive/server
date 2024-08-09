package com.example.temp.geo.dto;

import com.example.temp.common.entity.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindAddressResponse {
    private String fullText;
    private String x;
    private String y;
    private String zipNo;
    private String sido;
    private String sigungu;
    private String dong;

    public static FindAddressResponse of(Address address) {
        FindAddressResponse ret = new FindAddressResponse();
        ret.fullText = address.getFullText();
        ret.x = address.getX();
        ret.y = address.getY();
        ret.zipNo = address.getZipNo();
        ret.sido = address.getSido();
        ret.sigungu = address.getSigungu();
        ret.dong = address.getDong();
        return ret;
    }
}
