package com.azunitech.gw1.second.persistent.repository;

import com.azunitech.gw1.second.persistent.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {}
