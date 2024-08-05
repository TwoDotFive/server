package com.example.temp.chat.dto;

import java.time.LocalDateTime;

public record LastMessageView(String content, LocalDateTime time) {
}
