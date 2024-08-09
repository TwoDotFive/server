package com.example.temp.fanpool.service.impl;

import com.example.temp.baseball.domain.Game;
import com.example.temp.baseball.domain.GameRepository;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.fanpool.dto.FanpoolInformationView;
import com.example.temp.fanpool.service.CreateFanpoolService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CreateFanpoolServiceImpl implements CreateFanpoolService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final FanpoolRepository fanpoolRepository;

    @Override
    @Transactional
    public FanpoolInformationView doService(long userId, CreateFanpoolRequest request) {
        User hostUser = userRepository.findByIdOrElseThrow(userId);
        Game game = gameRepository.findByIdOrElseThrow(request.getGameId());
        Fanpool fanpool = Fanpool.build(hostUser, game, request);
        Fanpool saved = fanpoolRepository.save(fanpool);
        return new FanpoolInformationView(saved);
    }
}
