package com.example.temp.fanpool.dto;

import com.example.temp.baseball.dto.GameInformationView;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.user.dto.UserProfileView;
import lombok.Getter;

@Getter
public class FanpoolInformationView {
    private long id;
    private UserProfileView hostUser;
    private GameInformationView game;
    private int numberOfPeople;
    private int currentNumberOfPeople;

    public FanpoolInformationView(Fanpool fanpool) {
        this.id = fanpool.getId();
        hostUser = new UserProfileView(fanpool.getHostUser());
        game = new GameInformationView(fanpool.getGame());
        numberOfPeople = fanpool.getNumberOfPeople();
        currentNumberOfPeople = fanpool.getCurrentNumberOfPeople();
    }
}
