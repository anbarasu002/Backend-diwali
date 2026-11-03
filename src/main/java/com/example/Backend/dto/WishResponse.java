package com.example.Backend.dto;

import com.example.Backend.model.Wish;
import java.time.Instant;

public record WishResponse(
        String id,
        String name,
        String cracker,
        String sweet,
        String food,
        Instant createdAt
) {

    public static WishResponse from(Wish w) {

        return new WishResponse(
                w.getId(),
                w.getName(),
                w.getCracker(),
                w.getSweet(),
                w.getFood(),
                w.getCreatedAt()
        );
    }
}