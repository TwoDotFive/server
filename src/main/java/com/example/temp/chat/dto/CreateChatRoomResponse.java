package com.example.temp.chat.dto;

import com.example.temp.common.util.IdUtil;

public record CreateChatRoomResponse(String roomId) {

    public static CreateChatRoomResponse build(Long roomId) {
        return new CreateChatRoomResponse(IdUtil.toString(roomId));
    }
}
