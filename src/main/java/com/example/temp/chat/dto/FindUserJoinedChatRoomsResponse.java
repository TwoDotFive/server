package com.example.temp.chat.dto;

import com.example.temp.common.util.IdUtil;

import java.util.List;

public record FindUserJoinedChatRoomsResponse(String userId, List<ChatRoomView> rooms) {

    public static FindUserJoinedChatRoomsResponse build(long userId, List<ChatRoomView> rooms) {
        return new FindUserJoinedChatRoomsResponse(IdUtil.toString(userId), rooms);
    }
}
