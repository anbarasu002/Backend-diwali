package com.example.Backend.repository;

import com.example.Backend.model.Wish;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishRepository extends MongoRepository<Wish, String> {
}
