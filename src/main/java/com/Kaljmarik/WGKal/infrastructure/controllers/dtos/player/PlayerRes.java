package com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player;

import java.time.Instant;

public record PlayerRes(
        Long wotAccountId,
        String nickname,
        Instant createdAt,
        String realm,
        String accessToken,
        Instant expiresAt
) {
}
