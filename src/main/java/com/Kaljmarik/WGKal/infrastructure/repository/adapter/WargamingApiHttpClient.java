package com.Kaljmarik.WGKal.infrastructure.repository.adapter;

import com.Kaljmarik.WGKal.domain.model.OverallStats;
import com.Kaljmarik.WGKal.domain.model.Player;
import com.Kaljmarik.WGKal.domain.ports.WargamingApiClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Map;

@Component
public class WargamingApiHttpClient implements WargamingApiClient {

    private static final String APP_ID = "4176649637604c50ee76da2c35ff6950";
    private final WebClient webClient;

    public WargamingApiHttpClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.worldoftanks.eu/wot").build();
    }

    @Override
    public Player fetchPlayerInfo(String accessToken) {
        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/account/info/")
                        .queryParam("application_id", APP_ID)
                        .queryParam("access_token", accessToken)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || !response.containsKey("data")) {
            throw new IllegalStateException("Invalid response from Wargaming API");
        }

        Map<String, Object> dataMap = asMap(response.get("data"));
        Map<String, Object> data = asMap(dataMap.values().iterator().next());

        return new Player(
                parseLongSafe(data.get("account_id")),
                parseLongSafe(data.get("account_id")),
                parseStringSafe(data.get("nickname")),
                Instant.ofEpochSecond(parseLongSafe(data.get("created_at"))),
                Instant.ofEpochSecond(parseLongSafe(data.get("last_battle_time"))),
                parseIntegerSafe(data.get("global_rating")),
                "eu",
                accessToken,// accessToken
                null    // expiresAt
        );
    }

    @Override
    public OverallStats fetchOverallStats(String accessToken) {
        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/account/stats/")
                        .queryParam("application_id", APP_ID)
                        .queryParam("access_token", accessToken)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || !response.containsKey("data")) {
            throw new IllegalStateException("Invalid response from Wargaming API");
        }

        Map<String, Object> dataMap = asMap(response.get("data"));
        Map<String, Object> statsMap = asMap(dataMap.values().iterator().next());
        Map<String, Object> statistics = asMap(statsMap.get("statistics"));
        Map<String, Object> all = asMap(statistics.get("all"));

        return new OverallStats(
                parseLongSafe(statsMap.get("account_id")),
                parseLongSafe(all.get("battles")),
                parseLongSafe(all.get("wins")),
                parseLongSafe(all.get("losses")),
                parseLongSafe(all.getOrDefault("draws", 0)),
                parseLongSafe(all.getOrDefault("survived_battles", 0)),
                parseLongSafe(all.get("damage_dealt")),
                parseLongSafe(all.get("frags")),
                parseLongSafe(all.getOrDefault("shots", 0)),
                parseLongSafe(all.getOrDefault("spotted", 0)),
                parseLongSafe(all.getOrDefault("capture_points", 0)),
                parseLongSafe(all.getOrDefault("dropped_capture_points", 0)),
                Instant.now()
        );
    }

    private static long parseLongSafe(Object value) {
        if (value == null) return 0L;
        if (value instanceof Number) return ((Number) value).longValue();
        try { return Long.parseLong(value.toString()); }
        catch (NumberFormatException e) { return 0L; }
    }

    private static Integer parseIntegerSafe(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return ((Number) value).intValue();
        try { return Integer.parseInt(value.toString()); }
        catch (NumberFormatException e) { return null; }
    }

    private static String parseStringSafe(Object value) {
        return value != null ? value.toString() : "";
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> asMap(Object obj) {
        if (obj instanceof Map<?, ?> map) {
            return (Map<String, Object>) map;
        }
        throw new IllegalStateException("Expected a map but got: " + obj);
    }
}
