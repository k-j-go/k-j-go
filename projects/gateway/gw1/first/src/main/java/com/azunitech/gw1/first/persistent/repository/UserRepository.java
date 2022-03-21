package com.azunitech.gw1.first.persistent.repository;

import com.azunitech.gw1.first.persistent.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {}
