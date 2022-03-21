package com.azunitech.gw1.second.persistent;

import com.azunitech.gw1.second.application.EventSourceService;
import com.azunitech.gw1.second.domain.User;
import com.azunitech.gw1.second.domain.UserGateway;
import com.azunitech.gw1.second.persistent.model.UserDocument;
import com.azunitech.gw1.second.persistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserGatewayImpl implements UserGateway {
  UserRepository userRepository;
  EventSourceService eventSourceService;

  @Autowired
  public UserGatewayImpl(UserRepository userRepository, EventSourceService eventSourceService) {
    this.userRepository = userRepository;
    this.eventSourceService = eventSourceService;
  }

  @Override
  public List<User> loadAll() {
    return userRepository.findAll().stream().map(x -> x.toDomain()).collect(Collectors.toList());
  }

  @Override
  public User create(User user) {
    return userRepository
        .insert(UserDocument.builder().id(user.getId()).name(user.getName()).build())
        .toDomain();
  }
}
