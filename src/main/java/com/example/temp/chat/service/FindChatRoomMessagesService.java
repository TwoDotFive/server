package com.example.temp.chat.service;

import com.example.temp.chat.dto.ChatMessageView;
import com.example.temp.chat.dto.FindChatRoomMessagesCommand;

import java.util.List;

public interface FindChatRoomMessagesService {

    List<ChatMessageView> doService(FindChatRoomMessagesCommand command);
}
