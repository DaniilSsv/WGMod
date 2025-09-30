package com.Kaljmarik.WGKal.domain.model;

import java.time.Instant;

public record OverallStats(
        Long playerId,
        long battles,
        long wins,
        long losses,
        long draws,
        long survivedBattles,
        long damageDealt,
        long frags,
        long shots,
        long spotted,
        long capturePoints,
        long droppedCapturePoints,
        Instant updatedAt
) {

}

