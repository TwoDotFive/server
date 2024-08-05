package com.example.temp.chat.dto;

import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;

public record ChatUserProfileView(String id, String nickname, String image) {

    public static ChatUserProfileView of(User user) {
        if (user == null) return null;
        return new ChatUserProfileView(
                IdUtil.toString(user.getId()),
                user.getNickname(),
                user.getProfileImageUrl()
        );
    }
}
