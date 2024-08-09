package com.example.temp.geo.service.impl;

import com.example.temp.common.entity.Address;
import com.example.temp.common.exception.CustomException;
import com.example.temp.geo.dto.VworldCoord2AddressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/*
* 국토교통부 Open API
* https://www.vworld.kr/dev/v4dv_geocoderguide2_s002.do
* */
@Component
public class Coord2AddressClient {
    private static final String SERVICE_PARAM = "service";
    private static final String SERVICE_PARAM_VALUE = "address";
    private static final String REQUEST_PARAM = "request";
    private static final String REQUEST_PARAM_VALUE = "getAddress";
    private static final String KEY_PARAM = "key";
    private static final String POINT_PARAM = "point";
    private static final String RESPONSE_TYPE_PARAM ="type";
    private static final String RESPONSE_TYPE_PARAM_VALUE ="PARCEL";    //법정동

    @Value("${geo.vworld.coord2address-uri}")
    private String coord2AddressUri;
    @Value("${geo.vworld.rest-api-key}")
    private String restApiKey;

    public Address trans(String x, String y) {
        String point = x + "," + y;
        VworldCoord2AddressResponse result = WebClient.create(coord2AddressUri)
                .get()
                .uri(uriBuilder ->
                        uriBuilder.queryParam(POINT_PARAM, point)
                                .queryParam(KEY_PARAM, restApiKey)
                                .queryParam(SERVICE_PARAM, SERVICE_PARAM_VALUE)
                                .queryParam(REQUEST_PARAM, REQUEST_PARAM_VALUE)
                                .queryParam(RESPONSE_TYPE_PARAM, RESPONSE_TYPE_PARAM_VALUE)
                                .build()
                )
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Internal Server Error")))
                .bodyToMono(VworldCoord2AddressResponse.class)
                .block();
        return result.toEntity();
    }
}
