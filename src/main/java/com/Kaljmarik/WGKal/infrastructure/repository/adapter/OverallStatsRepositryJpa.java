package com.Kaljmarik.WGKal.infrastructure.repository.adapter;

import com.Kaljmarik.WGKal.domain.model.OverallStats;
import com.Kaljmarik.WGKal.domain.ports.OverallStatsRepository;
import com.Kaljmarik.WGKal.infrastructure.repository.jpa.overallstats.JpaOverallStatsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OverallStatsRepositryJpa implements OverallStatsRepository {

    private final JpaOverallStatsRepository jpaRepo;

    public OverallStatsRepositryJpa(JpaOverallStatsRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<OverallStats> findByPlayerId(Long playerId) {
        return jpaRepo.findByPlayerId(playerId)
                .map(entity -> new OverallStats(
                        entity.getPlayerId(),
                        entity.getBattles(),
                        entity.getWins(),
                        entity.getLosses(),
                        entity.getDraws(),
                        entity.getSurvivedBattles(),
                        entity.getDamageDealt(),
                        entity.getFrags(),
                        entity.getShots(),
                        entity.getSpotted(),
                        entity.getCapturePoints(),
                        entity.getDroppedCapturePoints(),
                        entity.getUpdatedAt()
                ));
    }

    @Override
    public OverallStats save(OverallStats stats) {
        return null;
    }
}
