package com.azunitech.gw1.second.domain;

import java.util.List;
/*
User Domain Entity persistent logic
 */
public interface UserGateway {
  List<User> loadAll();

  User create(User user);
}
