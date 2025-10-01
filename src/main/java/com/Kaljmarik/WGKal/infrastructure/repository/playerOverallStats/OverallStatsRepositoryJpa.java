package com.Kaljmarik.WGKal.infrastructure.repository.playerOverallStats;

import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;
import com.Kaljmarik.WGKal.domain.playerOverallStats.api.OverallStatsRepository;
import com.Kaljmarik.WGKal.infrastructure.repository.player.JpaPlayerRepository;
import com.Kaljmarik.WGKal.infrastructure.controllers.models.PlayerEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OverallStatsRepositoryJpa implements OverallStatsRepository {

    private final JpaOverallStatsRepository jpaStatsRepo;
    private final JpaPlayerRepository jpaPlayerRepo;

    public OverallStatsRepositoryJpa(JpaOverallStatsRepository jpaStatsRepo, JpaPlayerRepository jpaPlayerRepo) {
        this.jpaStatsRepo = jpaStatsRepo;
        this.jpaPlayerRepo = jpaPlayerRepo;
    }

    @Override
    public Optional<OverallStats> findById(Long wotAccountId) {
        return jpaStatsRepo.findById(wotAccountId)
                .map(OverallStatsMapper::toDomain);
    }

    @Override
    public OverallStats save(OverallStats stats) {
        PlayerEntity managedEntity = jpaPlayerRepo.findByWotAccountId(stats.wotAccountId())
                .orElseThrow(() -> new EntityNotFoundException("player not found"));

        return OverallStatsMapper.toDomain(jpaStatsRepo.save(OverallStatsMapper.toEntity(stats, managedEntity)));
    }
}
