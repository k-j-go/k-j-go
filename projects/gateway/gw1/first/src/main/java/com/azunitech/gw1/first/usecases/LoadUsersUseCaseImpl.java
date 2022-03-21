package com.azunitech.gw1.first.usecases;

import com.azunitech.gw1.first.domain.User;
import com.azunitech.gw1.first.domain.UserGateway;
import com.azunitech.gw1.first.usecases.api.LoadUsersUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadUsersUseCaseImpl implements LoadUsersUseCase {
  private final UserGateway userGateway;

  public LoadUsersUseCaseImpl(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  @Override
  public UsersResponse loadAll() {
    List<User> users = userGateway.loadAll();
    return UsersResponse.builder().users(users).build();
  }
}
