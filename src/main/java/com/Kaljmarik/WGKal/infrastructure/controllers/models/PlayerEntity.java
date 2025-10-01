package com.Kaljmarik.WGKal.infrastructure.controllers.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @Column(name = "wot_account_id")
    private Long wotAccountId;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(nullable = false)
    private String realm;

    @Column(name = "access_token", nullable = false, columnDefinition = "text")
    private String accessToken;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
}
