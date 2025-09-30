package com.Kaljmarik.WGKal.infrastructure.repository.adapter;

import com.Kaljmarik.WGKal.domain.model.Player;
import com.Kaljmarik.WGKal.domain.ports.PlayerRepository;
import com.Kaljmarik.WGKal.infrastructure.repository.jpa.player.JpaPlayerRepository;
import com.Kaljmarik.WGKal.infrastructure.repository.jpa.player.PlayerEntity;
import com.Kaljmarik.WGKal.infrastructure.repository.mapper.PlayerMapper;
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
        PlayerEntity entity = PlayerMapper.toEntity(player);

        entity = jpaRepo.save(entity);

        return PlayerMapper.toDomain(entity);
    }
}
