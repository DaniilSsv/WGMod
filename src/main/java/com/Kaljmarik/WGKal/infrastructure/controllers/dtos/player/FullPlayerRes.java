package com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player;

import com.Kaljmarik.WGKal.domain.player.Player;
import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;

public record FullPlayerRes(
        Player player,
        OverallStats overallStats,
        double winRatio,
        double kdRatio,
        double damageRatio
) {

}
