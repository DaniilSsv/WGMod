package com.Kaljmarik.WGKal.infrastructure.dtos.player;

import java.time.Instant;

public record PlayerRes(
        Long wotAccountId,
        String nickname,
        Instant createdAt,
        Instant lastBattleTime,
        Integer globalRating,
        String realm,
        String accessToken,
        Long expiresAt
) {
}
