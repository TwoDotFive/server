package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatRoom;
import com.example.temp.common.util.IdUtil;

import java.util.List;

public record ChatRoomView(
        String id,
        List<ChatUserProfileView> profiles,
        LastMessageView lastMessage
) {

    public static ChatRoomView of(ChatRoom chatRoom) {
        List<ChatUserProfileView> chatUserProfiles = chatRoom.getUserChatRooms().stream()
                .map(userChatRoom -> ChatUserProfileView.of(userChatRoom.getUser()))
                .toList();

        return new ChatRoomView(
                IdUtil.toString(chatRoom.getId()),
                chatUserProfiles,
                new LastMessageView(chatRoom.getLastMessage(), chatRoom.getLastMessageTime())
        );
    }
}
