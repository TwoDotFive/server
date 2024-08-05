package com.example.temp.chat.dto;

import com.example.temp.common.util.IdUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StompChatMessage {

    private String messageId;
    private String userId;
    private String type;
    private String content;

    public static StompChatMessage of(ChatMessageView chatMessageView) {
        StompChatMessage stompChatMessage = new StompChatMessage();
        stompChatMessage.messageId = IdUtil.toString(chatMessageView.messageId());
        stompChatMessage.userId = IdUtil.toString(chatMessageView.userId());
        stompChatMessage.type = chatMessageView.type().name();
        stompChatMessage.content = chatMessageView.content();
        return stompChatMessage;
    }
}
