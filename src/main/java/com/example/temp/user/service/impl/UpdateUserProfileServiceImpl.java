package com.example.temp.user.service.impl;

import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.domain.TeamRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.UpdatedUserProfileRequest;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.UpdateUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateUserProfileServiceImpl implements UpdateUserProfileService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    public UserProfileView doService(long userId, UpdatedUserProfileRequest updatedUserProfileRequest) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Team team = teamRepository.findByIdOrElseThrow(updatedUserProfileRequest.getFavoriteTeam());
        user.updateProfile(updatedUserProfileRequest);
        user.updateFavoriteTeam(team);
        return new UserProfileView(user);
    }
}
