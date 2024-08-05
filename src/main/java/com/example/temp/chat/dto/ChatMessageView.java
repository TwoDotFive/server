package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatMessage;
import com.example.temp.chat.domain.ChatMessageType;

public record ChatMessageView(Long messageId, Long userId, ChatMessageType type, String content) {

    public static ChatMessageView of(ChatMessage chatMessage) {
        return new ChatMessageView(
                chatMessage.getId(),
                chatMessage.getUserId(),
                chatMessage.getType(),
                chatMessage.getContent());
    }
}
