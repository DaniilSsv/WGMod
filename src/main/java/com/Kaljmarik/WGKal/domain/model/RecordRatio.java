package com.Kaljmarik.WGKal.domain.model;

public record RecordRatio(
        double winRatio,     // % of wins
        double kdRatio,      // frags / (battles-survived)
        double damageRatio   // avg damage per battle
) {
    public static RecordRatio from(long battles, long wins, long survivedBattles, long frags, long damageDealt) {
        double winRatio = battles > 0 ? (wins * 100.0 / battles) : 0.0;
        double kdRatio = (battles - survivedBattles) > 0 ? ((double) frags / (battles - survivedBattles)) : 0.0;
        double damageRatio = battles > 0 ? ((double) damageDealt / battles) : 0.0;

        return new RecordRatio(winRatio, kdRatio, damageRatio);
    }
}
