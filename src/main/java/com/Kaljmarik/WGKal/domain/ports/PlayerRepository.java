package com.Kaljmarik.WGKal.domain.ports;

import com.Kaljmarik.WGKal.domain.model.Player;

import java.util.Optional;

public interface PlayerRepository {
    Optional<Player> findByWotAccountId(Long wotAccountId);
    Player save(Player player);
}
