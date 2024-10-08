package com.example.temp.chat.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ChatroomUserRepository extends Repository<ChatroomUser, Long> {

    void save(ChatroomUser entity);

    @Modifying
    @Query("UPDATE ChatroomUser c SET c.lastActivityTime = :time WHERE c.userId = :userId AND c.roomId = :chatroomId")
    void updateLastActivityTime(@Param("userId") long userId, @Param("chatroomId") long chatroomId, @Param("time") LocalDateTime time);

    boolean existsByUserIdAndRoomId(long userId, long roomId);

    void deleteByUserIdAndRoomId(long userId, long roomId);
}
