package com.Kaljmarik.WGKal.infrastructure.dtos.player;

import java.time.Instant;

public record FullPlayerRes(
        Long wotAccountId,
        String accessToken,
        String nickname,
        Instant createdAt,
        Instant lastBattleTime,
        Integer globalRating,
        String realm,
        long battles,
        long wins,
        long losses,
        long survivedBattles,
        long damageDealt,
        long frags,
        double winRatio,
        double kdRatio,
        double damageRatio
) {

}
