package com.Kaljmarik.WGKal.domain.ports;

import com.Kaljmarik.WGKal.domain.model.OverallStats;

import java.util.Optional;

public interface OverallStatsRepository {
    Optional<OverallStats> findByPlayerId(Long playerId);
    OverallStats save(OverallStats stats);
}
