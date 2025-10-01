package com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player;

import com.Kaljmarik.WGKal.domain.player.Player;

public record FullPlayerRes(
        Player player,
        long battles,
        long wins,
        long losses,
        long survivedBattles,
        long damageDealt,
        long frags,
        double winRatio,
        double kdRatio,
        double damageRatio
) {

}
