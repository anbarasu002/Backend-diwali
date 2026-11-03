package com.example.Backend.controller;

import com.example.Backend.dto.CrackerPlayRequest;
import com.example.Backend.service.CatalogService;
import com.example.Backend.service.CrackerPlayService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CrackerController {

    private final CatalogService catalog;
    private final CrackerPlayService plays;

    public CrackerController(CatalogService catalog, CrackerPlayService plays) {
        this.catalog = catalog;
        this.plays = plays;
    }

    @GetMapping("/crackers")
    public List<CatalogService.Cracker> crackers() { return catalog.crackers(); }

    @GetMapping("/colors")
    public List<CatalogService.Color> colors() { return catalog.colors(); }

    /** Called each time a cracker is lit in the playground. */
    @PostMapping("/crackers/plays")
    @ResponseStatus(HttpStatus.CREATED)
    public void play(@Valid @RequestBody CrackerPlayRequest request) {
        plays.record(request);
    }
}
