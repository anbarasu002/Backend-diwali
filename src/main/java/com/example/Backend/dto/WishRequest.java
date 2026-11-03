package com.example.Backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Same keys as the frontend's answers object.
 * Same 40-character limit as its inputs.
 */
public record WishRequest(

        @NotBlank @Size(max = 40) String name,

        @NotBlank @Size(max = 40) String cracker,

        @NotBlank @Size(max = 40) String sweet,

        @NotBlank @Size(max = 40) String food

) {
}