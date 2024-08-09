package com.example.temp.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;
    private String name;
    private String representativeImageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stadium stadium;
}
