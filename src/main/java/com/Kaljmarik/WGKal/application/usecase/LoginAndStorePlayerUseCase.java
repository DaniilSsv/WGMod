package com.Kaljmarik.WGKal.application.usecase;

import com.Kaljmarik.WGKal.application.util.TimeUtil;
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

    public PlayerRes execute(String accessToken, Long accountId, String nickname, Long expiresAtUnix) {
        Player existing = playerRepository.findByWotAccountId(accountId).orElse(null);

        Player playerToSave = (existing != null)
                ? new Player(
                existing.id(),
                accountId,
                nickname,
                existing.createdAt(),
                existing.lastBattleTime(),
                existing.globalRating(),
                existing.realm(),
                accessToken,
                TimeUtil.fromUnix(expiresAtUnix)
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
                TimeUtil.fromUnix(expiresAtUnix)
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
