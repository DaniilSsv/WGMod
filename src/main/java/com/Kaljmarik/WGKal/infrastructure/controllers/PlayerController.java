package com.Kaljmarik.WGKal.infrastructure.controllers;

import com.Kaljmarik.WGKal.domain.playerOverallStats.GetAndUpdatePlayerUseCase;
import com.Kaljmarik.WGKal.infrastructure.controllers.dtos.player.FullPlayerRes;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final GetAndUpdatePlayerUseCase retrieveUseCase;

    @GetMapping("/fetch")
    public FullPlayerRes fetchPlayer(@RequestParam Long wotAccountId) {
        return retrieveUseCase.execute(wotAccountId);
    }
}
