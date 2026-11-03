package com.example.Backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.Backend.service.CatalogService;
import org.junit.jupiter.api.Test;

/** Pure unit test: needs no database, so `mvnw package` works without MONGODB_URI. */
class CatalogServiceTest {

    private final CatalogService catalog = new CatalogService();

    @Test
    void knowsTheFrontendCrackersAndColors() {
        assertEquals(4, catalog.crackers().size());
        assertEquals(5, catalog.colors().size());
        assertTrue(catalog.hasCracker("sparkler"));
        assertTrue(catalog.hasColor("gold"));
    }

    @Test
    void rejectsUnknownIds() {
        assertFalse(catalog.hasCracker("nope"));
        assertFalse(catalog.hasColor("purple"));
    }
}
