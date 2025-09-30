package com.Kaljmarik.WGKal.infrastructure.repository.jpa.overallstats;

import com.Kaljmarik.WGKal.infrastructure.repository.jpa.player.PlayerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "player_overall_stats")
public class OverallStatsEntity {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @Column(nullable = false)
    private long battles;

    @Column(nullable = false)
    private long wins;

    @Column(nullable = false)
    private long losses;

    @Column(nullable = false)
    private long draws;

    @Column(name = "survived_battles", nullable = false)
    private long survivedBattles;

    @Column(name = "damage_dealt", nullable = false)
    private long damageDealt;

    @Column(nullable = false)
    private long frags;

    @Column(nullable = false)
    private long shots;

    @Column(nullable = false)
    private long spotted;

    @Column(name = "capture_points", nullable = false)
    private long capturePoints;

    @Column(name = "dropped_capture_points", nullable = false)
    private long droppedCapturePoints;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public OverallStatsEntity() {}

    public OverallStatsEntity(PlayerEntity player, long battles, long wins, long losses,
                              long draws, long survivedBattles, long damageDealt, long frags,
                              long shots, long spotted, long capturePoints, long droppedCapturePoints,
                              Instant updatedAt) {
        this.player = player;
        this.playerId = player.getId();
        this.battles = battles;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.survivedBattles = survivedBattles;
        this.damageDealt = damageDealt;
        this.frags = frags;
        this.shots = shots;
        this.spotted = spotted;
        this.capturePoints = capturePoints;
        this.droppedCapturePoints = droppedCapturePoints;
        this.updatedAt = updatedAt;
    }
}
