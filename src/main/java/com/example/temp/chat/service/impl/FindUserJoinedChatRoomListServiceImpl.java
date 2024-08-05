package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.UserChatRoomRepository;
import com.example.temp.chat.dto.ChatRoomView;
import com.example.temp.chat.service.FindUserJoinedChatRoomListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserJoinedChatRoomListServiceImpl implements FindUserJoinedChatRoomListService {

    private final UserChatRoomRepository userChatRoomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ChatRoomView> doService(long userId) {
        return userChatRoomRepository.findByUserIdAndDeletedFalse(userId)
                .stream()
                .map(userChatRoom -> ChatRoomView.of(userChatRoom.getChatRoom()))
                .toList();
    }
}
