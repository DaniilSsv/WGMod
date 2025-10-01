package com.Kaljmarik.WGKal.infrastructure.repository.wargaming;

import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;
import com.Kaljmarik.WGKal.domain.player.Player;

import java.time.Instant;
import java.util.Map;

public class WgOverallStatsMapper {

    @SuppressWarnings("unchecked")
    public static OverallStats mapToOverallStats(Player player, Map<String, Object> json) {
        try {
            Map<String, Object> data = (Map<String, Object>) ((Map<?, ?>) json.get("data"))
                    .get(String.valueOf(player.wotAccountId()));

            if (data == null) {
                throw new IllegalStateException("No stats found for account " + player.wotAccountId());
            }

            long clanId = ((Number) data.getOrDefault("clan_id", 0)).longValue();
            Instant createdAt = Instant.ofEpochSecond(((Number) data.getOrDefault("created_at", 0)).longValue());
            int globalRating = ((Number) data.getOrDefault("global_rating", 0)).intValue();
            Instant lastBattleTime = Instant.ofEpochSecond(((Number) data.getOrDefault("last_battle_time", 0)).longValue());

            Map<String, Object> stats = (Map<String, Object>) data.getOrDefault("statistics", Map.of());
            Map<String, Object> all = (Map<String, Object>) stats.getOrDefault("all", Map.of());

            return new OverallStats(
                    player.wotAccountId(),
                    null, // version
                    player,
                    clanId,
                    createdAt,
                    globalRating,
                    lastBattleTime,

                    ((Number) all.getOrDefault("battles", 0)).intValue(),
                    ((Number) all.getOrDefault("wins", 0)).intValue(),
                    ((Number) all.getOrDefault("losses", 0)).intValue(),
                    ((Number) all.getOrDefault("draws", 0)).intValue(),
                    ((Number) all.getOrDefault("survived_battles", 0)).intValue(),
                    ((Number) all.getOrDefault("frags", 0)).intValue(),
                    ((Number) all.getOrDefault("damage_dealt", 0)).intValue(),

                    ((Number) all.getOrDefault("avg_damage_blocked", 0)).doubleValue(),
                    ((Number) all.getOrDefault("avg_damage_assisted", 0)).doubleValue(),
                    ((Number) all.getOrDefault("battle_avg_xp", 0)).doubleValue(),

                    ((Number) all.getOrDefault("max_damage_tank_id", 0)).intValue(),
                    ((Number) all.getOrDefault("max_damage", 0)).intValue(),

                    ((Number) all.getOrDefault("max_xp_tank_id", 0)).intValue(),
                    ((Number) all.getOrDefault("max_xp", 0)).intValue(),

                    ((Number) all.getOrDefault("max_frags_tank_id", 0)).intValue(),
                    ((Number) all.getOrDefault("max_frags", 0)).intValue(),

                    Instant.now()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to map WG API response to OverallStats", e);
        }
    }
}
