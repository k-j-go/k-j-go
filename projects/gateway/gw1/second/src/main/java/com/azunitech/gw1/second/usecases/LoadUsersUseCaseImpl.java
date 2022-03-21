package com.azunitech.gw1.second.usecases;

import com.azunitech.gw1.second.domain.User;
import com.azunitech.gw1.second.domain.UserGateway;
import com.azunitech.gw1.second.usecases.api.LoadUsersUseCase;
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
