package com.example.temp.chat.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChatRoom extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    private LocalDateTime lastExitTime;

    private boolean deleted;

    public static UserChatRoom build(User user, ChatRoom chatRoom) {
        UserChatRoom userChatRoom = new UserChatRoom();
        userChatRoom.id = IdUtil.create();
        userChatRoom.user = user;
        userChatRoom.chatRoom = chatRoom;
        userChatRoom.lastExitTime = LocalDateTime.now();
        userChatRoom.deleted = false;
        return userChatRoom;
    }

    public void setDeleted() {
        deleted = true;
    }

    public void updateLastExitTime() {
        lastExitTime = LocalDateTime.now();
    }
}
