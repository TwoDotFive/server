package com.example.temp.chat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stomp")
public record StompProperties(
        String handshakeEndpoint,
        String subscribeDestinationPrefix,
        String publishDestinationPrefix
) {
}
