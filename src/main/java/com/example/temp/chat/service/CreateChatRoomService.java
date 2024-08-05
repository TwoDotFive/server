package com.example.temp.chat.service;

import com.example.temp.chat.dto.CreateChatRoomCommand;

public interface CreateChatRoomService {

    Long doService(CreateChatRoomCommand command);
}
