package com.example.Backend.controller;

import com.example.Backend.dto.StatsResponse;
import com.example.Backend.service.CrackerPlayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final CrackerPlayService plays;

    public StatsController(CrackerPlayService plays) { this.plays = plays; }

    @GetMapping
    public StatsResponse stats() { return plays.stats(); }
}
