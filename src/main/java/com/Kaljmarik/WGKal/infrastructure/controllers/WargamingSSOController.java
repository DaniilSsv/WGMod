package com.Kaljmarik.WGKal.infrastructure.controllers;

import com.Kaljmarik.WGKal.domain.wargaming.LoginAndStorePlayerUseCase;
import com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player.PlayerRes;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/api/auth")
public class WargamingSSOController {

    private final LoginAndStorePlayerUseCase loginUseCase;
    private static final String APP_ID = "4176649637604c50ee76da2c35ff6950";
    private static final String REDIRECT_URI = "http://81.165.138.193:9999/api/auth/callback";

    @GetMapping("/login")
    public String login() {
        String loginUrl = String.format(
                "https://api.worldoftanks.eu/wot/auth/login/?application_id=%s&redirect_uri=%s",
                APP_ID, REDIRECT_URI
        );
        return "redirect:" + loginUrl;
    }

    @GetMapping("/callback")
    public ResponseEntity<PlayerRes> callback(
            @RequestParam("access_token") String accessToken,
            @RequestParam("account_id") Long accountId,
            @RequestParam("nickname") String nickname,
            @RequestParam("expires_at") Long expiresAt
    ) {
        PlayerRes player = loginUseCase.execute(accessToken, accountId, nickname, expiresAt);
        return ResponseEntity.ok(player);
    }
}