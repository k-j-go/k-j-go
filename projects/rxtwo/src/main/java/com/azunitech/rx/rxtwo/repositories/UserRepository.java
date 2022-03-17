package com.azunitech.rx.rxtwo.repositories;


import com.azunitech.rx.rxtwo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

  // Spring converts this to Regex findByFirstnameRegex(String firstname)  {"firstname" : {"$regex"
  // : firstname }}
  // automatically
  public List<User> findByFirstName(String firstName);
}
