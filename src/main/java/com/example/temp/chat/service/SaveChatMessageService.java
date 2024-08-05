package com.example.temp.chat.service;

import com.example.temp.chat.dto.SaveChatMessageCommand;

public interface SaveChatMessageService {

    void doService(SaveChatMessageCommand command);
}
