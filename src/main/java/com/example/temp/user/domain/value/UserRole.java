package com.example.temp.user.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    UNAUTH("미인증"),
    USER("유저"),

    WITHDRAW("탈퇴"),

    ADMIN("어드민");

    private final String text;

    public boolean isAdmin() {
        return this.equals(ADMIN);
    }
}
