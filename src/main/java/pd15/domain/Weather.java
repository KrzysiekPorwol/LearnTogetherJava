package pd15.domain;

import java.time.LocalDateTime;

public record Weather(LocalDateTime time, Double temperatureCelsius, Double rainMm) {
}
