package com.example.temp.baseball.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Season {
    @Id
    private Long id;
    @Column(name = "in_year")
    private Integer year;
    private String name;
    private String representativeImageUrl;
}
