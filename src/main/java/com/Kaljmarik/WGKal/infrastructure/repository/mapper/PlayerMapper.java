package com.Kaljmarik.WGKal.infrastructure.repository.mapper;

import com.Kaljmarik.WGKal.domain.model.Player;
import com.Kaljmarik.WGKal.infrastructure.repository.jpa.player.PlayerEntity;

public class PlayerMapper {

    public static Player toDomain(PlayerEntity entity) {
        if (entity == null) return null;
        return new Player(
                entity.getId(),
                entity.getWotAccountId(),
                entity.getNickname(),
                entity.getCreatedAt(),
                entity.getLastBattleTime(),
                entity.getGlobalRating(),
                entity.getRealm(),
                entity.getAccessToken(),
                entity.getExpiresAt()
        );
    }

    public static PlayerEntity toEntity(Player player) {
        if (player == null) return null;

        Long id = null;
        if (player.id() != null) {
            id = player.id();
        }

        return new PlayerEntity(
                id,
                player.wotAccountId(),
                player.nickname(),
                player.createdAt(),
                player.lastBattleTime(),
                player.globalRating(),
                player.realm(),
                player.expiresAt(),
                player.accessToken()
        );
    }
}
