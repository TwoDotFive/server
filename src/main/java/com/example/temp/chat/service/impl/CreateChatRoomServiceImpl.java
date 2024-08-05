package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.ChatRoom;
import com.example.temp.chat.domain.ChatRoomRepository;
import com.example.temp.chat.domain.UserChatRoom;
import com.example.temp.chat.dto.CreateChatRoomCommand;
import com.example.temp.chat.service.CreateChatRoomService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateChatRoomServiceImpl implements CreateChatRoomService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public Long doService(CreateChatRoomCommand command) {
        User host = userRepository.findByIdOrElseThrow(command.hostId());
        User guest = userRepository.findByIdOrElseThrow(command.guestId());
        ChatRoom chatRoom = createChatRoom(host, guest);
        chatRoomRepository.save(chatRoom);
        return chatRoom.getId();
    }

    private ChatRoom createChatRoom(User host, User guest) {
        ChatRoom chatRoom = ChatRoom.create();
        UserChatRoom hostChatRoom = UserChatRoom.build(host, chatRoom);
        UserChatRoom guestChatRoom = UserChatRoom.build(guest, chatRoom);
        chatRoom.addUserChatRoom(hostChatRoom);
        chatRoom.addUserChatRoom(guestChatRoom);
        return chatRoom;
    }
}
