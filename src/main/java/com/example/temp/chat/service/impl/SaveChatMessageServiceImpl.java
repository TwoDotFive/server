package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.ChatMessage;
import com.example.temp.chat.domain.ChatMessageRepository;
import com.example.temp.chat.domain.ChatRoom;
import com.example.temp.chat.domain.ChatRoomRepository;
import com.example.temp.chat.dto.SaveChatMessageCommand;
import com.example.temp.chat.service.SaveChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*  [최적화 필요]
- 채팅 메시지 저장소 : RDB -> NoSQL
- 채팅방 마지막 메시지 갱신 주기
 */

@Service
@RequiredArgsConstructor
public class SaveChatMessageServiceImpl implements SaveChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public void doService(SaveChatMessageCommand command) {
        ChatMessage chatMessage = ChatMessage.build(
                command.roomId(),
                command.userId(),
                command.messageType(),
                command.content()
        );
        chatMessageRepository.save(chatMessage);
        updateChatRoomLastMessage(command);
    }

    private void updateChatRoomLastMessage(SaveChatMessageCommand command) {
        ChatRoom chatRoom = chatRoomRepository.findByIdOrElseThrow(command.roomId());
        chatRoom.updateLastMessageInfo(command.content());
    }
}
