package com.example.temp.chat.dto;

import java.util.List;

public record FindChatRoomMessagesResponse(List<StompChatMessage> messages) {

    public static FindChatRoomMessagesResponse build(List<StompChatMessage> messages) {
        return new FindChatRoomMessagesResponse(messages);
    }
}
