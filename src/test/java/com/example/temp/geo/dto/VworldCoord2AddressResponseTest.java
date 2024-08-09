package com.example.temp.geo.dto;

import com.example.temp.common.entity.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VworldCoord2AddressResponseTest {

    @Test
    @DisplayName("JSON이 잘 맵핑되는가")
    void toEntity() throws JsonProcessingException {
        String json = "{"
                + "  \"response\": {"
                + "    \"service\": {"
                + "      \"name\": \"address\","
                + "      \"version\": \"2.0\","
                + "      \"operation\": \"getAddress\","
                + "      \"time\": \"6(ms)\""
                + "    },"
                + "    \"status\": \"OK\","
                + "    \"input\": {"
                + "      \"point\": {"
                + "        \"x\": \"126.978275264\","
                + "        \"y\": \"37.566642192\""
                + "      },"
                + "      \"crs\": \"\","
                + "      \"type\": \"parcel\""
                + "    },"
                + "    \"result\": ["
                + "      {"
                + "        \"zipcode\": \"04524\","
                + "        \"type\": \"parcel\","
                + "        \"text\": \"서울특별시 중구 태평로1가 31\","
                + "        \"structure\": {"
                + "          \"level0\": \"대한민국\","
                + "          \"level1\": \"서울특별시\","
                + "          \"level2\": \"중구\","
                + "          \"level3\": \"\","
                + "          \"level4L\": \"태평로1가\","
                + "          \"level4LC\": \"1114010300\","
                + "          \"level4A\": \"명동\","
                + "          \"level4AC\": \"1114055000\","
                + "          \"level5\": \"31\","
                + "          \"detail\": \"\""
                + "        }"
                + "      }"
                + "    ]"
                + "  }"
                + "}";

        ObjectMapper objectMapper = new ObjectMapper();
        VworldCoord2AddressResponse response = objectMapper.readValue(json, VworldCoord2AddressResponse.class);
        Address address = response.toEntity();

        assertNotNull(address);
        assertEquals("126.978275264", address.getX());
        assertEquals("37.566642192", address.getY());
        assertEquals("04524", address.getZipNo());
        assertEquals("서울특별시 중구 태평로1가 31", address.getFullText());
        assertEquals("서울특별시", address.getSido());
        assertEquals("태평로1가", address.getDong());
    }
}