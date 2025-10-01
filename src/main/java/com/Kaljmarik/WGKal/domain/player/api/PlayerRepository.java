package com.Kaljmarik.WGKal.domain.player.api;

import com.Kaljmarik.WGKal.domain.player.Player;

import java.util.Optional;

public interface PlayerRepository {
    Optional<Player> findByWotAccountId(Long wotAccountId);
    Player save(Player player);
}
