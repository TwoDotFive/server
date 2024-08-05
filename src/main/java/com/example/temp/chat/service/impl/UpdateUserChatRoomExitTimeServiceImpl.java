package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.UserChatRoom;
import com.example.temp.chat.domain.UserChatRoomRepository;
import com.example.temp.chat.service.UpdateUserChatRoomExitTimeService;
import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserChatRoomExitTimeServiceImpl implements UpdateUserChatRoomExitTimeService {

    private final UserChatRoomRepository userChatRoomRepository;

    @Override
    @Transactional
    public void doService(Long userId, Long roomId) {
        UserChatRoom userChatRoom = userChatRoomRepository.findByUserIdAndChatRoomId(userId, roomId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Chat Room User Not Found"));
        userChatRoom.updateLastExitTime();
    }
}
