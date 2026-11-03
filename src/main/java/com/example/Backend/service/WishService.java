package com.example.Backend.service;

import com.example.Backend.dto.WishRequest;
import com.example.Backend.dto.WishResponse;
import com.example.Backend.exception.NotFoundException;
import com.example.Backend.model.Wish;
import com.example.Backend.repository.WishRepository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishRepository repo;

    public WishService(WishRepository repo) {
        this.repo = repo;
    }

    public WishResponse create(WishRequest r) {

        Wish saved = repo.save(
            new Wish(
                r.name().trim(),
                r.cracker().trim(),
                r.sweet().trim(),
                r.food().trim()
            )
        );

        return WishResponse.from(saved);
    }

    public WishResponse get(String id) {

        return repo.findById(id)
                .map(WishResponse::from)
                .orElseThrow(() ->
                    new NotFoundException("Wish not found: " + id));
    }

    public List<WishResponse> list(int page, int size) {

        int safeSize = Math.min(Math.max(size, 1), 100);

        return repo.findAll(
                PageRequest.of(
                    Math.max(page, 0),
                    safeSize,
                    Sort.by(Sort.Direction.DESC, "createdAt")
                )
        ).map(WishResponse::from).getContent();
    }

    public void delete(String id) {

        if (!repo.existsById(id)) {
            throw new NotFoundException("Wish not found: " + id);
        }

        repo.deleteById(id);
    }

    public long count() {
        return repo.count();
    }
}