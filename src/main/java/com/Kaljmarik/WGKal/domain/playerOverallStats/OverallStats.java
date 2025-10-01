package com.Kaljmarik.WGKal.domain.playerOverallStats;

import com.Kaljmarik.WGKal.domain.player.Player;

import java.time.Instant;

public record OverallStats(
        long wotAccountId,
        Long version,
        Player player,
        long clanId,
        Instant createdAt,
        int globalRating,
        Instant lastBattleTime,
        int battles,
        int wins,
        int losses,
        int draws,
        int survivedBattles,
        int damageDealt,
        int frags,
        int shots,
        int spotted,
        int capturePoints,
        int droppedCapturePoints,
        Instant updatedAt
) {
}

