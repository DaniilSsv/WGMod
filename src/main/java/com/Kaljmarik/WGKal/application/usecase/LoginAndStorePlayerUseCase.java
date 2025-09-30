package com.Kaljmarik.WGKal.application.usecase;

import com.Kaljmarik.WGKal.domain.model.Player;
import com.Kaljmarik.WGKal.domain.ports.PlayerRepository;
import com.Kaljmarik.WGKal.infrastructure.dtos.player.PlayerRes;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoginAndStorePlayerUseCase {

    private final PlayerRepository playerRepository;

    public LoginAndStorePlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerRes execute(String accessToken, Long accountId, String nickname, Long expiresAt) {
        // Look for existing player
        Player existing = playerRepository.findByWotAccountId(accountId).orElse(null);

        Player playerToSave = (existing != null)
                ? new Player(
                existing.id(),         // preserve ID
                accountId,
                nickname,              // update nickname
                existing.createdAt(),
                existing.lastBattleTime(),
                existing.globalRating(),
                existing.realm(),
                accessToken,           // update token
                expiresAt              // update expires
        )
                : new Player(
                null,                  // new player
                accountId,
                nickname,
                Instant.now(),
                null,
                null,
                "eu",
                accessToken,
                expiresAt
        );

        Player savedPlayer = playerRepository.save(playerToSave);

        return new PlayerRes(
                savedPlayer.wotAccountId(),
                savedPlayer.nickname(),
                savedPlayer.createdAt(),
                savedPlayer.lastBattleTime(),
                savedPlayer.globalRating(),
                savedPlayer.realm(),
                savedPlayer.accessToken(),
                savedPlayer.expiresAt()
        );
    }
}
