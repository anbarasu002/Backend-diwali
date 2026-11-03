package com.example.Backend.dto;

import jakarta.validation.constraints.NotBlank;

public record CrackerPlayRequest(@NotBlank String crackerId, @NotBlank String colorId) {
}
