package com.example.Backend.controller;

import com.example.Backend.config.AdminGuard;
import com.example.Backend.dto.WishRequest;
import com.example.Backend.dto.WishResponse;
import com.example.Backend.service.WishService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService service;
    private final AdminGuard admin;

    public WishController(WishService service, AdminGuard admin) {
        this.service = service;
        this.admin = admin;
    }

    /** Called when the user finishes the four questions. */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishResponse create(@Valid @RequestBody WishRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public WishResponse get(@PathVariable String id) {
        return service.get(id);
    }

    /** Admin only: lists everyone's answers. */
    @GetMapping
    public List<WishResponse> list(@RequestHeader(value = "X-Admin-Key", required = false) String key,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size) {
        admin.require(key);
        return service.list(page, size);
    }

    /** Admin only. */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader(value = "X-Admin-Key", required = false) String key,
                       @PathVariable String id) {
        admin.require(key);
        service.delete(id);
    }
}
