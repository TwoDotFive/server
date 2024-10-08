package com.example.temp.user.dto;

import com.example.temp.geo.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAuthenticatedLocationRequest {
    private boolean representative;

    private String x;
    private String y;
    private String fullText;
    private String zipNo;
    private String sido;
    private String sigungu;
    private String dong;
    private String dongCd;

    public Address toEntity() {
        return Address.builder()
                .x(x)
                .y(y)
                .fullText(fullText)
                .zipNo(zipNo)
                .sido(sido)
                .sigungu(sigungu)
                .dongCd(dongCd)
                .dong(dong)
                .build();
    }

    public boolean isRepresentativeLocation() {
        return this.representative;
    }
}
