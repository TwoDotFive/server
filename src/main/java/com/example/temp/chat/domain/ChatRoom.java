package com.example.temp.chat.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserChatRoom> userChatRooms = new HashSet<>();

    private String lastMessage;

    private LocalDateTime lastMessageTime;

    public static ChatRoom create() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.id = IdUtil.create();
        return chatRoom;
    }

    public void addUserChatRoom(UserChatRoom userChatRoom) {
        userChatRooms.add(userChatRoom);
    }

    public void updateLastMessageInfo(String lastMessage) {
        this.lastMessage = lastMessage;
        this.lastMessageTime = LocalDateTime.now();
    }
}
