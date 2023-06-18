package com.intraway.testcore.adapter.out.persistence.mongo.repository;

import com.intraway.testcore.adapter.out.persistence.mongo.model.FizzBuzzModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FizzBuzzRepository extends MongoRepository<FizzBuzzModel, String> {}
