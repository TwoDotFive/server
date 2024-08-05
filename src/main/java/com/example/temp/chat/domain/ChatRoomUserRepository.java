package com.example.temp.chat.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ChatRoomUserRepository extends Repository<UserChatRoom, Long> {

    UserChatRoom save(UserChatRoom entity);

    Optional<UserChatRoom> findById(Long id);

    default UserChatRoom findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Chat Room User Not Found"));
    }
}
