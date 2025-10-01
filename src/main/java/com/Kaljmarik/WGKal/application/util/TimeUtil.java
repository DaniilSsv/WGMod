package com.Kaljmarik.WGKal.application.util;

import java.time.Instant;

public class TimeUtil {
    public static Instant fromUnix(Long unixTime) {
        return Instant.ofEpochSecond(unixTime);
    }
}
