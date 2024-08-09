package com.example.temp.baseball.domain;

import com.example.temp.common.entity.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stadium {
    @Id
    private Long id;
    @Embedded
    private Address address;
    private String aliasAddr;

    public String getStadiumName() {
        return address.getBuildName();
    }
}
