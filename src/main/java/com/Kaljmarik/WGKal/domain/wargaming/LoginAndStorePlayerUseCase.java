package com.Kaljmarik.WGKal.domain.wargaming;

import com.Kaljmarik.WGKal.domain.util.TimeUtil;
import com.Kaljmarik.WGKal.domain.player.Player;
import com.Kaljmarik.WGKal.domain.player.api.PlayerRepository;
import com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player.PlayerRes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class LoginAndStorePlayerUseCase {
    @Autowired
    private final PlayerRepository playerRepository;

    public LoginAndStorePlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerRes execute(String accessToken, Long accountId, String nickname, Long expiresAtUnix) {
        Player existing = playerRepository.findByWotAccountId(accountId).orElse(null);

        Player playerToSave = update(existing, accessToken, accountId, nickname, expiresAtUnix);

        Player savedPlayer = playerRepository.save(playerToSave);

        return new PlayerRes(
                savedPlayer.wotAccountId(),
                savedPlayer.nickname(),
                savedPlayer.updatedAt(),
                savedPlayer.realm(),
                savedPlayer.accessToken(),
                savedPlayer.expiresAt()
        );
    }

    private Player update(Player existing, String accessToken, Long accountId, String nickname, Long expiresAtUnix) {
        if (existing != null) {
            return new Player(
                    existing.wotAccountId(),
                    existing.nickname(),
                    Instant.now(),
                    existing.realm(),
                    accessToken,
                    TimeUtil.fromUnix(expiresAtUnix)
            );
        } else {
            return new Player(
                    accountId,
                    nickname,
                    Instant.now(),
                    "eu",
                    accessToken,
                    TimeUtil.fromUnix(expiresAtUnix)
            );
        }
    }
}
