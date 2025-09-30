package com.Kaljmarik.WGKal.application.usecase;

import com.Kaljmarik.WGKal.domain.model.OverallStats;
import com.Kaljmarik.WGKal.domain.model.Player;
import com.Kaljmarik.WGKal.domain.model.RecordRatio;
import com.Kaljmarik.WGKal.domain.ports.OverallStatsRepository;
import com.Kaljmarik.WGKal.domain.ports.PlayerRepository;
import com.Kaljmarik.WGKal.domain.ports.WargamingApiClient;
import com.Kaljmarik.WGKal.infrastructure.dtos.player.FullPlayerRes;
import com.Kaljmarik.WGKal.infrastructure.dtos.player.PlayerRes;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RetrieveAndStorePlayerUseCase {

    private final WargamingApiClient wargamingApiClient;
    private final PlayerRepository playerRepository;
    private final OverallStatsRepository statsRepository;

    public RetrieveAndStorePlayerUseCase(WargamingApiClient wargamingApiClient,
                                              PlayerRepository playerRepository,
                                              OverallStatsRepository statsRepository) {
        this.wargamingApiClient = wargamingApiClient;
        this.playerRepository = playerRepository;
        this.statsRepository = statsRepository;
    }

    public FullPlayerRes execute(Long wotAccountId) {
        Player player = playerRepository.findByWotAccountId(wotAccountId)
                .orElseThrow(() -> new IllegalStateException("Player not found"));

        OverallStats stats = wargamingApiClient.fetchOverallStats(player.accessToken());

        stats = new OverallStats(
                player.id(),
                stats.battles(),
                stats.wins(),
                stats.losses(),
                stats.draws(),
                stats.survivedBattles(),
                stats.damageDealt(),
                stats.frags(),
                stats.shots(),
                stats.spotted(),
                stats.capturePoints(),
                stats.droppedCapturePoints(),
                Instant.now()
        );
        statsRepository.save(stats);

        RecordRatio ratios = RecordRatio.from(
                stats.battles(),
                stats.wins(),
                stats.survivedBattles(),
                stats.frags(),
                stats.damageDealt()
        );

        return new FullPlayerRes(
                player.wotAccountId(),
                player.accessToken(),
                player.nickname(),
                player.createdAt(),
                player.lastBattleTime(),
                player.globalRating(),
                player.realm(),
                stats.battles(),
                stats.wins(),
                stats.losses(),
                stats.survivedBattles(),
                stats.damageDealt(),
                stats.frags(),
                ratios.winRatio(),
                ratios.kdRatio(),
                ratios.damageRatio()
        );
    }
}
