package com.example.Backend.controller;

import java.util.Map;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** GET /api/health pings MongoDB Atlas, so it verifies the real database connection. */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final MongoTemplate mongo;

    public HealthController(MongoTemplate mongo) { this.mongo = mongo; }

    @GetMapping
    public ResponseEntity<Map<String, String>> health() {
        try {
            mongo.getDb().runCommand(new Document("ping", 1));
            return ResponseEntity.ok(Map.of("status", "UP", "database", mongo.getDb().getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("status", "DOWN", "database", "unreachable"));
        }
    }
}
