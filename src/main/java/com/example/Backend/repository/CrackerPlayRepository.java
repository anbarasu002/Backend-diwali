package com.example.Backend.repository;

import com.example.Backend.model.CrackerPlay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrackerPlayRepository extends MongoRepository<CrackerPlay, String> {
    long countByCrackerId(String crackerId);
    long countByColorId(String colorId);
}
