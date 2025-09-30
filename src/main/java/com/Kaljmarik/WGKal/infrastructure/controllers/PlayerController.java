package com.Kaljmarik.WGKal.infrastructure.controllers;

import com.Kaljmarik.WGKal.application.usecase.RetrieveAndStorePlayerUseCase;
import com.Kaljmarik.WGKal.infrastructure.dtos.player.FullPlayerRes;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final RetrieveAndStorePlayerUseCase retrieveUseCase;

    @GetMapping("/fetch")
    public FullPlayerRes fetchPlayer(@RequestParam Long wotAccountId) {
        return retrieveUseCase.execute(wotAccountId);
    }
}
