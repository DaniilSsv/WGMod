package com.Kaljmarik.WGKal.domain.ports;

import com.Kaljmarik.WGKal.domain.model.OverallStats;
import com.Kaljmarik.WGKal.domain.model.Player;

public interface WargamingApiClient {
    Player fetchPlayerInfo(String accessToken);         // via SSO
    OverallStats fetchOverallStats(String accessToken); // via SSO
}
