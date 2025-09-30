package com.Kaljmarik.WGKal.infrastructure.repository.jpa.overallstats;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaOverallStatsRepository extends JpaRepository<OverallStatsEntity, Long> {
    Optional<OverallStatsEntity> findByPlayerId(Long playerId);
}
