package com.Kaljmarik.WGKal.domain.playerOverallStats;

import com.Kaljmarik.WGKal.domain.player.Player;
import com.Kaljmarik.WGKal.domain.player.api.PlayerRepository;
import com.Kaljmarik.WGKal.domain.playerOverallStats.api.OverallStatsRepository;
import com.Kaljmarik.WGKal.domain.util.StatsRatio;
import com.Kaljmarik.WGKal.domain.wargaming.api.WargamingApiClient;
import com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player.FullPlayerRes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetAndUpdatePlayerUseCase {
    @Autowired
    private final WargamingApiClient wargamingApiClient;
    private final PlayerRepository playerRepository;
    private final OverallStatsRepository statsRepository;

    public GetAndUpdatePlayerUseCase(WargamingApiClient wargamingApiClient,
                                     PlayerRepository playerRepository,
                                     OverallStatsRepository statsRepository) {
        this.wargamingApiClient = wargamingApiClient;
        this.playerRepository = playerRepository;
        this.statsRepository = statsRepository;
    }

    public FullPlayerRes execute(Long wotAccountId) {
        Player player = playerRepository.findByWotAccountId(wotAccountId)
                .orElseThrow(() -> new IllegalStateException("Player not found"));

        OverallStats fetchedOverallStats = wargamingApiClient.fetchOverallStats(player);
        OverallStats existingStats = statsRepository.findById(fetchedOverallStats.wotAccountId())
                        .orElse(null);

        OverallStats updated = update(existingStats, fetchedOverallStats);
        statsRepository.save(updated);

        StatsRatio ratios = StatsRatio.from(
                fetchedOverallStats.battles(),
                fetchedOverallStats.wins(),
                fetchedOverallStats.survivedBattles(),
                fetchedOverallStats.frags(),
                fetchedOverallStats.damageDealt()
        );

        return new FullPlayerRes(
                player,
                fetchedOverallStats,
                ratios.winRatio(),
                ratios.kdRatio(),
                ratios.damageRatio()
        );
    }

    private OverallStats update(OverallStats existingStats, OverallStats fetchedOverallStats) {
        if (existingStats != null) {
            return new OverallStats(
                    existingStats.wotAccountId(),
                    existingStats.version(),
                    fetchedOverallStats.player(),
                    fetchedOverallStats.clanId(),
                    existingStats.createdAt(),
                    fetchedOverallStats.globalRating(),
                    fetchedOverallStats.lastBattleTime(),
                    fetchedOverallStats.battles(),
                    fetchedOverallStats.wins(),
                    fetchedOverallStats.losses(),
                    fetchedOverallStats.draws(),
                    fetchedOverallStats.survivedBattles(),
                    fetchedOverallStats.frags(),
                    fetchedOverallStats.damageDealt(),
                    fetchedOverallStats.avarageDamageBlocked(),
                    fetchedOverallStats.avarageDamageAssisted(),
                    fetchedOverallStats.avarageBattleExperience(),
                    fetchedOverallStats.maxDamageTankId(),
                    fetchedOverallStats.maxDamage(),
                    fetchedOverallStats.maxXpTankId(),
                    fetchedOverallStats.maxXp(),
                    fetchedOverallStats.maxFragsTankId(),
                    fetchedOverallStats.maxFrags(),
                    fetchedOverallStats.updatedAt()
            );
        } else {
            return fetchedOverallStats;
        }
    }
}
