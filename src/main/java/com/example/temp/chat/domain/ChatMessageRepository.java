package com.example.temp.chat.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ChatMessageRepository extends Repository<ChatMessage, Long> {

    void save(ChatMessage entity);

    @Query(nativeQuery = true, value = "SELECT * FROM chat_message c " +
            "WHERE c.chat_room_id = :roomId AND c.id < :lastId " +
            "ORDER BY c.id DESC " +
            "LIMIT :pageSize"
    )
    List<ChatMessage> findByPage(Long roomId, long lastId, int pageSize);
}
