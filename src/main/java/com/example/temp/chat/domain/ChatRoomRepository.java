package com.example.temp.chat.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ChatRoomRepository extends Repository<ChatRoom, Long> {

    ChatRoom save(ChatRoom entity);

    Optional<ChatRoom> findById(Long id);

    default ChatRoom findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Chat Room Not Found"));
    }

    void deleteById(Long id);

}
