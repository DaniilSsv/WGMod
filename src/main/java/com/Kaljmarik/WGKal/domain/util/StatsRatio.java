package com.Kaljmarik.WGKal.domain.util;

public record StatsRatio(
        double winRatio,     // % of wins
        double kdRatio,      // frags / (battles-survived)
        double damageRatio   // avg damage per battle
) {
    public static StatsRatio from(int battles, int wins, int survivedBattles, int frags, int damageDealt) {
        double winRatio = battles > 0 ? (wins * 100.0 / battles) : 0.0;
        double kdRatio = (battles - survivedBattles) > 0 ? ((double) frags / (battles - survivedBattles)) : 0.0;
        double damageRatio = battles > 0 ? ((double) damageDealt / battles) : 0.0;

        return new StatsRatio(winRatio, kdRatio, damageRatio);
    }
}
