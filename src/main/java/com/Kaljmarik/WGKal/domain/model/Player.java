package com.Kaljmarik.WGKal.domain.model;

import java.time.Instant;

public record Player(
        Long id,
        Long wotAccountId,
        String nickname,
        Instant createdAt,
        Instant lastBattleTime,
        Integer globalRating,
        String realm,
        String accessToken,
        Long expiresAt
) {}

