package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatMessageType;
import com.example.temp.common.util.IdUtil;

public record SaveChatMessageCommand(Long roomId, Long userId, ChatMessageType messageType, String content) {

    public static SaveChatMessageCommand build(String roomId, Long userId, String messageType, String content) {
        return new SaveChatMessageCommand(
                IdUtil.toLong(roomId),
                userId,
                ChatMessageType.valueOf(messageType),
                content);
    }
}
