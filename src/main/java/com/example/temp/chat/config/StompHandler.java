package com.example.temp.chat.config;

import com.example.temp.chat.service.UpdateUserChatRoomExitTimeService;
import com.example.temp.common.exception.CustomException;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.service.impl.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.messaging.simp.stomp.StompCommand.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class StompHandler implements ChannelInterceptor {

    private final JwtTokenService jwtTokenService;
    private final StompProperties stompProperties;
    private final UpdateUserChatRoomExitTimeService updateUserChatRoomExitTimeService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (command == CONNECT) {
            // 최초 연결 시, 세션 속성에 사용자 ID 설정
            Long userId = extractUserIdFromJwt(accessor);
            addSessionAttribute(accessor, "userId", userId);
        } else if (command == SUBSCRIBE) {
            // 채팅방 구독 시, 세션 속성에 구독한 채팅방 ID 설정
            Long roomId = parseRoomId(accessor, stompProperties.subscribeDestinationPrefix());
            addSessionAttribute(accessor, "roomId", roomId);
        } else if (command == SEND) {
            // 채팅 전송 시, 목적지가 세션 속성에 설정된 채팅방 ID와 일치하는지 검증
            Long destinationRoomId = parseRoomId(accessor, stompProperties.publishDestinationPrefix());
            Long connectedRoomId = (Long) getSessionAttribute(accessor, "roomId");
            if (!destinationRoomId.equals(connectedRoomId)) {
                throw new CustomException(HttpStatus.FORBIDDEN, "Wrong Destination");
            }
        } else if (command == DISCONNECT) {
            // 소켓 연결 종료 시, 회원이 채팅방을 나간 시각 기록
            Long userId = (Long) getSessionAttribute(accessor, "userId");
            Long roomId = (Long) getSessionAttribute(accessor, "roomId");
            updateUserChatRoomExitTimeService.doService(userId, roomId);
        }
        return message;
    }

    private long extractUserIdFromJwt(StompHeaderAccessor accessor) {
        String accessToken = accessor.getFirstNativeHeader("Authorization");
        if (!jwtTokenService.isTokenValid(accessToken)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }
        return jwtTokenService.getUserId(accessToken);
    }

    private Long parseRoomId(StompHeaderAccessor accessor, String destinationPrefix) {
        String destination = accessor.getDestination();
        if (destination == null || destination.length() <= destinationPrefix.length() + 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Room Id Not Found");
        }
        return IdUtil.toLong(destination.substring(destinationPrefix.length() + 1));
    }

    private Object getSessionAttribute(StompHeaderAccessor accessor, String key) {
        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        if (sessionAttributes == null) {
            log.error("SessionAttributes is Null");
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
        if (!sessionAttributes.containsKey(key)) {
            log.error("SessionAttribute '{}' Not Found", key);
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
        return sessionAttributes.get(key);
    }

    private <T> void addSessionAttribute(StompHeaderAccessor accessor, String key, T value) {
        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        if (sessionAttributes == null) {
            log.error("SessionAttributes is Null");
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
        sessionAttributes.put(key, value);
    }

}
