package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatMessagePreview;

import java.time.LocalDateTime;

public record FindChatroomListResult(
        String roomId,
        boolean isHost,
        LocalDateTime lastActivityTime,
        UserProfileView partner,
        String teams,
        ChatMessagePreview lastMessage
) {

    public static FindChatroomListResult of(FindChatroomListNativeDto dto) {

        UserProfileView partnerProfile = null;

        if (dto.getPartnerId() != null) {
            partnerProfile = UserProfileView.builder()
                    .id(dto.getPartnerId().toString())
                    .nickname(dto.getPartnerNickname())
                    .image(dto.getPartnerImage())
                    .build();
        }

        return new FindChatroomListResult(
                dto.getRoomId().toString(),
                dto.getIsHost() == 1,
                dto.getLastActivityTime(),
                partnerProfile,
                dto.getTeams(),
                new ChatMessagePreview(dto.getLastMessageContent(), dto.getLastMessageTime())
        );
    }
}
