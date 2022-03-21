package com.azunitech.gw1.second.usecases.api;

import com.azunitech.gw1.second.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
Load all users from persistent
 */
public interface LoadUsersUseCase {
  UsersResponse loadAll();

  @Builder
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class UsersResponse {
    List<User> users;
  }
}
