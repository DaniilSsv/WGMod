package com.Kaljmarik.WGKal.domain.player;

import java.time.Instant;

public record Player(
        Long wotAccountId,
        String nickname,
        Instant updatedAt,
        String realm,
        String accessToken,
        Instant expiresAt
) {}

