package com.example.temp.chat.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseTimeEntity {

    @Id
    private Long id;

    private Long chatRoomId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private ChatMessageType type;

    private String content;

    public static ChatMessage build(Long chatRoomId, Long userId, ChatMessageType type, String content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.id = IdUtil.create();
        chatMessage.chatRoomId = chatRoomId;
        chatMessage.userId = userId;
        chatMessage.type = type;
        chatMessage.content = content;
        return chatMessage;
    }

}
