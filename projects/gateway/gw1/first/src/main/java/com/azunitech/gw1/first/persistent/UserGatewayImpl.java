package com.azunitech.gw1.first.persistent;

import com.azunitech.gw1.first.application.EventSourceService;
import com.azunitech.gw1.first.domain.EventSource;
import com.azunitech.gw1.first.domain.User;
import com.azunitech.gw1.first.domain.UserGateway;
import com.azunitech.gw1.first.persistent.model.UserDocument;
import com.azunitech.gw1.first.persistent.repository.UserRepository;
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
