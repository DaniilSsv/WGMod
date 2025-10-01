package com.Kaljmarik.WGKal.infrastructure.repository.playerOverallStats;

import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;
import com.Kaljmarik.WGKal.infrastructure.controllers.models.OverallStatsEntity;
import com.Kaljmarik.WGKal.infrastructure.controllers.models.PlayerEntity;
import com.Kaljmarik.WGKal.infrastructure.repository.player.PlayerMapper;

public class OverallStatsMapper {
    public static OverallStats toDomain(OverallStatsEntity entity) {
        if (entity == null) return null;

        return new OverallStats(
                entity.getWotAccountId(),
                entity.getVersion(),
                PlayerMapper.toDomain(entity.getPlayer()),
                entity.getClanId(),
                entity.getCreatedAt(),
                entity.getGlobalRating(),
                entity.getLastBattleTime(),
                entity.getBattles(),
                entity.getWins(),
                entity.getLosses(),
                entity.getDraws(),
                entity.getSurvivedBattles(),
                entity.getFrags(),
                entity.getDamageDealt(),
                entity.getAvarageDamageBlocked(),
                entity.getAvarageDamageAssisted(),
                entity.getAvarageBattleExperience(),
                entity.getMaxDamageTankId(),
                entity.getMaxDamage(),
                entity.getMaxXpTankId(),
                entity.getMaxXp(),
                entity.getMaxFragsTankId(),
                entity.getMaxFrags(),
                entity.getUpdatedAt()
        );
    }

    public static OverallStatsEntity toEntity(OverallStats overallStats, PlayerEntity managedEntity) {
        if (overallStats == null) return null;

        return OverallStatsEntity.builder()
                .wotAccountId(overallStats.wotAccountId())
                .version(overallStats.version())
                .player(managedEntity) // @OneToOne
                .clanId(overallStats.clanId())
                .createdAt(overallStats.createdAt())
                .globalRating(overallStats.globalRating())
                .lastBattleTime(overallStats.lastBattleTime())
                .battles(overallStats.battles())
                .wins(overallStats.wins())
                .losses(overallStats.losses())
                .draws(overallStats.draws())
                .survivedBattles(overallStats.survivedBattles())
                .frags(overallStats.frags())
                .damageDealt(overallStats.damageDealt())
                .avarageDamageBlocked(overallStats.avarageDamageBlocked())
                .avarageDamageAssisted(overallStats.avarageDamageAssisted())
                .avarageBattleExperience(overallStats.avarageBattleExperience())
                .maxDamageTankId(overallStats.maxDamageTankId())
                .maxDamage(overallStats.maxDamage())
                .maxXpTankId(overallStats.maxXpTankId())
                .maxXp(overallStats.maxXp())
                .maxFragsTankId(overallStats.maxFragsTankId())
                .maxFrags(overallStats.maxFrags())
                .updatedAt(overallStats.updatedAt())
                .build();
    }
}
