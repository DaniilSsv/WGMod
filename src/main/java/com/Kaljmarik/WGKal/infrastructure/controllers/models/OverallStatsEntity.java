package com.Kaljmarik.WGKal.infrastructure.controllers.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player_overall_stats")
public class OverallStatsEntity {

    @Id
    @Column(name = "wot_account_id")
    private Long wotAccountId;

    @Version
    private Long version;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wot_account_id")
    private PlayerEntity player;

    @Column(name = "clan_id")
    private Long clanId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "global_rating")
    private int globalRating;

    @Column(name = "last_battle_time")
    private Instant lastBattleTime;

    @Column
    private int battles;

    @Column
    private int wins;

    @Column
    private int losses;

    @Column
    private int draws;

    @Column(name = "survived_battles")
    private int survivedBattles;

    @Column(name = "damage_dealt")
    private int damageDealt;

    @Column
    private int frags;

    @Column
    private int shots;

    @Column
    private int spotted;

    @Column(name = "capture_points")
    private int capturePoints;

    @Column(name = "dropped_capture_points")
    private int droppedCapturePoints;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
