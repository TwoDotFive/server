package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.ChatMessageRepository;
import com.example.temp.chat.domain.UserChatRoomRepository;
import com.example.temp.chat.dto.ChatMessageView;
import com.example.temp.chat.dto.FindChatRoomMessagesCommand;
import com.example.temp.chat.service.FindChatRoomMessagesService;
import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindChatRoomMessagesServiceImpl implements FindChatRoomMessagesService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserChatRoomRepository userChatRoomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessageView> doService(FindChatRoomMessagesCommand command) {
        if (!userChatRoomRepository.existsByUserIdAndChatRoomId(command.userId(), command.roomId())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Not Chat Room User");
        }
        return chatMessageRepository.findByPage(command.roomId(), command.lastMessageId(), command.pageSize())
                .stream()
                .map(ChatMessageView::of)
                .toList();
    }
}
