package com.example.temp.chat.service;

import com.example.temp.chat.dto.ChatRoomView;

import java.util.List;

public interface FindUserJoinedChatRoomListService {

    List<ChatRoomView> doService(long userId);
}
