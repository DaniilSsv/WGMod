package com.Kaljmarik.WGKal.domain.util;

import java.time.Instant;

public class TimeUtil {
    public static Instant fromUnix(Long unixTime) {
        return Instant.ofEpochSecond(unixTime);
    }
}
