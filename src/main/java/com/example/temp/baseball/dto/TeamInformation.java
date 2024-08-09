package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Team;
import lombok.Getter;

@Getter
public class TeamInformation {
    private final long id;
    private final String name;
    private final String representativeImageUrl;
    private final String stadiumName;
    private final String stadiumAliasName;

    public TeamInformation(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.representativeImageUrl = team.getRepresentativeImageUrl();
        this.stadiumName = team.getStadium().getStadiumName();
        this.stadiumAliasName = team.getStadium().getAliasAddr();
    }

}
