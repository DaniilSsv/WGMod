package com.Kaljmarik.WGKal.infrastructure.repository.player;

import com.Kaljmarik.WGKal.domain.player.Player;
import com.Kaljmarik.WGKal.infrastructure.controllers.models.PlayerEntity;

public class PlayerMapper {

    public static Player toDomain(PlayerEntity entity) {
        if (entity == null) return null;

        return new Player(
                entity.getWotAccountId(),
                entity.getNickname(),
                entity.getUpdatedAt(),
                entity.getRealm(),
                entity.getAccessToken(),
                entity.getExpiresAt()
        );
    }

    public static PlayerEntity toEntity(Player player) {
        if (player == null) return null;

        return PlayerEntity.builder()
                .wotAccountId(player.wotAccountId())
                .nickname(player.nickname())
                .updatedAt(player.updatedAt())
                .realm(player.realm())
                .expiresAt(player.expiresAt())
                .accessToken(player.accessToken())
                .build();
    }
}
