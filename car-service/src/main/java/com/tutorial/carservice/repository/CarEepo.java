package com.tutorial.carservice.repository;

import com.tutorial.carservice.identity.Car;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarEepo extends MongoRepository<Car, ObjectId> {
    List<Car> findByUserId(String userId);
    Optional<Car> findByUserIdAndId(String userId, String id);
}
