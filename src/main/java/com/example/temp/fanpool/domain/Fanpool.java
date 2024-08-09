package com.example.temp.fanpool.domain;

import com.example.temp.baseball.domain.Game;
import com.example.temp.common.entity.Address;
import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.fanpool.domain.value.FanpoolType;
import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Fanpool extends BaseTimeEntity {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User hostUser;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @Embedded
    private Address place;
    private LocalDateTime time;
    private Integer numberOfPeople;
    private Integer currentNumberOfPeople;
    private FanpoolType fanpoolType;
    private String memo;

    public static Fanpool build(User hostUser, Game game, CreateFanpoolRequest request) {
        Fanpool ret = new Fanpool();
        ret.id = IdUtil.create();
        ret.game = game;
        ret.hostUser = hostUser;
        ret.time = request.getTime();
        ret.numberOfPeople = request.getNumberOfPeople();
        ret.currentNumberOfPeople = 0;
        ret.fanpoolType = request.getFanpoolType();
        ret.memo = request.getMemo();
        // x, y 좌표 변환 후 Address로 저장
        return ret;
    }
}
