package com.Kaljmarik.WGKal.infrastructure.repository.playerOverallStats;

import com.Kaljmarik.WGKal.infrastructure.controllers.models.OverallStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOverallStatsRepository extends JpaRepository<OverallStatsEntity, Long> {
}
