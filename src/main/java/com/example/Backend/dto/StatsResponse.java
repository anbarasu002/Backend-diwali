package com.example.Backend.dto;

import java.util.Map;

public record StatsResponse(long totalWishes, long totalPlays,
                            Map<String, Long> playsByCracker, Map<String, Long> playsByColor) {
}
