package com.Kaljmarik.WGKal.domain.playerOverallStats.api;

import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;

import java.util.Optional;

public interface OverallStatsRepository {
    Optional<OverallStats> findById(Long wotAccountId);
    OverallStats save(OverallStats stats);
}
