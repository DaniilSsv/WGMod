package com.Kaljmarik.WGKal.infrastructure.repository.jpa.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wot_account_id", nullable = false, unique = true)
    private Long wotAccountId;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "last_battle_time")
    private Instant lastBattleTime;

    @Column(name = "global_rating")
    private Integer globalRating;

    @Column(nullable = false)
    private String realm;

    @Column(name = "access_token", nullable = false, columnDefinition = "text")
    private String accessToken;

    @Column(name = "expires_at", nullable = false)
    private Long expiresAt;

    public PlayerEntity() {}

    public PlayerEntity(Long id, Long wotAccountId, String nickname, Instant createdAt,
                        Instant lastBattleTime, Integer globalRating, String realm, Long expiresAt, String accessToken) {
        this.id = id;
        this.wotAccountId = wotAccountId;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.lastBattleTime = lastBattleTime;
        this.globalRating = globalRating;
        this.realm = realm;
        this.expiresAt = expiresAt;
        this.accessToken = accessToken;
    }

}
