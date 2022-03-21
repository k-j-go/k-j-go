package com.azunitech.gw1.second.usecases.api;

import com.azunitech.gw1.second.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Create a new user
 */
public interface CreateUserUseCase {
  CreateUserResponse create(CreateUserRequest request);

  @Builder
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class CreateUserResponse {
    private User user;
  }

  @Builder
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class CreateUserRequest {
    private String name;
  }
}
