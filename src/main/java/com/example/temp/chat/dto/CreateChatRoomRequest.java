package com.example.temp.chat.dto;

import com.example.temp.common.util.IdUtil;

public record CreateChatRoomRequest(String guestId) {

    public CreateChatRoomCommand toCommand(Long userId) {
        return new CreateChatRoomCommand(userId, IdUtil.toLong(guestId));
    }
}
