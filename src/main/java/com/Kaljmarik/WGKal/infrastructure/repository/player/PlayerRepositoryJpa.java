package com.Kaljmarik.WGKal.infrastructure.repository.player;

import com.Kaljmarik.WGKal.domain.player.Player;
import com.Kaljmarik.WGKal.domain.player.api.PlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlayerRepositoryJpa implements PlayerRepository {

    private final JpaPlayerRepository jpaRepo;

    public PlayerRepositoryJpa(JpaPlayerRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<Player> findByWotAccountId(Long wotAccountId) {
        return jpaRepo.findByWotAccountId(wotAccountId)
                .map(PlayerMapper::toDomain);
    }

    @Override
    public Player save(Player player) {
        return PlayerMapper.toDomain(jpaRepo.save(PlayerMapper.toEntity(player)));
    }
}
