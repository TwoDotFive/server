package com.example.temp.geo.dto;

import com.example.temp.common.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@NoArgsConstructor
@Getter
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class VworldCoord2AddressResponse {
    private Response response;

    public Address toEntity() {
        log.debug("api 성공 여부 {}", response.getStatus());
        if (!response.getStatus().equals("OK")) {
            return Address.builder().build();
        }

        return Address.builder()
                .x(response.getX())
                .y(response.getY())
                .zipNo(response.getZipNo())
                .fullText(response.getFullText())
                .sigungu(response.getSigungu())
                .sido(response.getSido())
                .dong(response.getEupmyundong())
                .build();
    }

    @NoArgsConstructor
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Response {
        private String status;      // OK, NOT_FOUND, ERROR
        private Input input;
        private List<Result> result;

        private String getX() {
            return String.valueOf(input.point.getX());
        }

        private String getY() {
            return String.valueOf(input.point.getY());
        }

        private String getSido() {
            return result.get(0).structure.sido;
        }

        private String getSigungu() {
            return result.get(0).structure.sigungu + " " + result.get(0).structure.gu;
        }

        private String getEupmyundong() {
            return result.get(0).structure.eupmyundong;
        }

        private String getDetail() {
            return result.get(0).structure.detail;
        }

        private String getZipNo() {
            return result.get(0).zipcode;
        }

        private String getFullText() {
            return result.get(0).text;
        }

        @NoArgsConstructor
        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class Input {
            private Point point;
            private String type; // ROAD, PARCEL, BOTH

            @NoArgsConstructor
            @Getter
            private static class Point {
                private double x;
                private double y;
            }
        }

        @NoArgsConstructor
        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class Result {
            private String zipcode;
            private String type;
            private String text;
            private Structure structure;

            @NoArgsConstructor
            @Getter
            private static class Structure {
                private String level0;
                @JsonProperty("level1")
                private String sido;
                @JsonProperty("level2")
                private String sigungu;
                @JsonProperty("level3")
                private String gu;
                @JsonProperty("level4L")
                private String eupmyundong;        // 법정읍면동
                @JsonProperty("level4LC")
                private String eupmyundongCd;
                private String level4A;
                private String level4AC;        // 행정읍,면,동 코드
                private String level5;          // (도로)길 (지번)번지
                private String detail;          // 상세 주소
            }
        }
    }
}



