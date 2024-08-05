package com.example.temp.chat.dto;

import com.example.temp.common.util.IdUtil;

public record FindChatRoomMessagesCommand(Long userId, Long roomId, Long lastMessageId, int pageSize) {

    public static FindChatRoomMessagesCommand build(long userId, String roomId, String lastMessageId, Integer pageSize) {
        return new FindChatRoomMessagesCommand(
                userId,
                IdUtil.toLong(roomId),
                IdUtil.toLong(lastMessageId),
                pageSize
        );
    }
}
