package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.ChatRoom;
import com.example.temp.chat.domain.ChatRoomRepository;
import com.example.temp.chat.domain.UserChatRoom;
import com.example.temp.chat.service.LeaveChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LeaveChatRoomServiceImpl implements LeaveChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public void doService(long userId, long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findByIdOrElseThrow(roomId);
        boolean isRoomEmpty = true;
        for (UserChatRoom userChatRoom : chatRoom.getUserChatRooms()) {
            if (userChatRoom.getUser().getId().equals(userId)) {
                userChatRoom.setDeleted();
            } else if (!userChatRoom.isDeleted()) {
                isRoomEmpty = false;
            }
        }
        if (isRoomEmpty) {
            chatRoomRepository.deleteById(chatRoom.getId());
        }
    }
}
