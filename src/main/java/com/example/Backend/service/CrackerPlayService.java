package com.example.Backend.service;

import com.example.Backend.dto.CrackerPlayRequest;
import com.example.Backend.dto.StatsResponse;
import com.example.Backend.model.CrackerPlay;
import com.example.Backend.repository.CrackerPlayRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CrackerPlayService {

    private final CrackerPlayRepository repo;
    private final CatalogService catalog;
    private final WishService wishes;

    public CrackerPlayService(CrackerPlayRepository repo, CatalogService catalog, WishService wishes) {
        this.repo = repo;
        this.catalog = catalog;
        this.wishes = wishes;
    }

    public void record(CrackerPlayRequest r) {
        if (!catalog.hasCracker(r.crackerId())) throw new IllegalArgumentException("Unknown crackerId: " + r.crackerId());
        if (!catalog.hasColor(r.colorId())) throw new IllegalArgumentException("Unknown colorId: " + r.colorId());
        repo.save(new CrackerPlay(r.crackerId(), r.colorId()));
    }

    public StatsResponse stats() {
        Map<String, Long> byCracker = new LinkedHashMap<>();
        catalog.crackers().forEach(c -> byCracker.put(c.id(), repo.countByCrackerId(c.id())));
        Map<String, Long> byColor = new LinkedHashMap<>();
        catalog.colors().forEach(c -> byColor.put(c.id(), repo.countByColorId(c.id())));
        return new StatsResponse(wishes.count(), repo.count(), byCracker, byColor);
    }
}
