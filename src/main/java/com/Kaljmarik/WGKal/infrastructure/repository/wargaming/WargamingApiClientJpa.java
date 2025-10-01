package com.Kaljmarik.WGKal.infrastructure.repository.wargaming;

import com.Kaljmarik.WGKal.domain.playerOverallStats.OverallStats;
import com.Kaljmarik.WGKal.domain.player.Player;
import com.Kaljmarik.WGKal.domain.wargaming.api.WargamingApiClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

import static com.Kaljmarik.WGKal.infrastructure.repository.wargaming.WgOverallStatsMapper.mapToOverallStats;

@Repository
public class WargamingApiClientJpa implements WargamingApiClient {

    private static final String APP_ID = "4176649637604c50ee76da2c35ff6950";
    private static final String BASE_URL = "https://api.worldoftanks.eu/wot/account/info/";

    private final WebClient webClient;

    public WargamingApiClientJpa(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(BASE_URL).build();
    }

    @Override
    public OverallStats fetchOverallStats(Player player) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> {
                        var builder = uriBuilder
                                .queryParam("application_id", APP_ID)
                                .queryParam("account_id", player.wotAccountId())
                                .queryParamIfPresent("access_token",
                                        (player.accessToken() != null && !player.accessToken().isBlank())
                                                ? java.util.Optional.of(player.accessToken())
                                                : java.util.Optional.empty());
                        return builder.build();
                    })
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(json -> mapToOverallStats(player, json))
                    .block(); // convert Mono<OverallStats> -> OverallStats
        } catch (WebClientResponseException e) {
            throw new RuntimeException("WG API HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch overall stats from WG API", e);
        }
    }
}
