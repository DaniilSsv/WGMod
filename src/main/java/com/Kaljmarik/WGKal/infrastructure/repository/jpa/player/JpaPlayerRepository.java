package com.Kaljmarik.WGKal.infrastructure.repository.jpa.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByWotAccountId(Long wotAccountId);
}
