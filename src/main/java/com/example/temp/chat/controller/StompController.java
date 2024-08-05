package com.example.temp.chat.controller;

import com.example.temp.chat.dto.SaveChatMessageCommand;
import com.example.temp.chat.dto.StompChatMessage;
import com.example.temp.chat.service.SaveChatMessageService;
import com.example.temp.common.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {

    private final SaveChatMessageService saveChatMessageService;

    @SendTo("/chat/sub/{roomId}")
    @MessageMapping("/{roomId}")
    public StompChatMessage chat(
            @DestinationVariable String roomId,
            StompChatMessage message,
            StompHeaderAccessor accessor
    ) {
        Long userId = (Long) accessor.getSessionAttributes().get("userId");
        SaveChatMessageCommand command = SaveChatMessageCommand.build(roomId, userId, message.getType(), message.getContent());
        saveChatMessageService.doService(command);
        message.setUserId(IdUtil.toString(userId));
        return message;
    }
}
