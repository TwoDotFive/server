package com.example.temp.chat.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface UserChatRoomRepository extends Repository<UserChatRoom, Long> {

    UserChatRoom save(UserChatRoom entity);

    Optional<UserChatRoom> findById(Long id);

    default UserChatRoom findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.FORBIDDEN, "Chat Room User Not Found"));
    }

    List<UserChatRoom> findByUserId(Long userId);

    List<UserChatRoom> findByUserIdAndDeletedFalse(Long userId);

    Optional<UserChatRoom> findByUserIdAndChatRoomId(Long userId, Long roomId);

    boolean existsByUserIdAndChatRoomId(Long userId, Long roomId);
}
