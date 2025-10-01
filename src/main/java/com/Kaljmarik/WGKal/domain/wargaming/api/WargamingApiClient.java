package com.Kaljmarik.WGKal.domain.wargaming.api;

import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;
import com.Kaljmarik.WGKal.domain.player.Player;

public interface WargamingApiClient {
    OverallStats fetchOverallStats(Player player);
}
