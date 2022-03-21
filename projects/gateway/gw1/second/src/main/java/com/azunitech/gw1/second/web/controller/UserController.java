package com.azunitech.gw1.second.web.controller;

import com.azunitech.gw1.second.application.BuildUserNameService;
import com.azunitech.gw1.second.application.IDGenerator;
import com.azunitech.gw1.second.domain.User;
import com.azunitech.gw1.second.usecases.api.CreateUserUseCase;
import com.azunitech.gw1.second.usecases.api.LoadUsersUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
  private final IDGenerator idGenerator;
  private final BuildUserNameService buildUserNameService;
  private final LoadUsersUseCase loadUsersUseCase;
  private final CreateUserUseCase createUserUseCase;

  public UserController(
      IDGenerator idGenerator,
      BuildUserNameService buildUserNameService,
      LoadUsersUseCase loadUsersUseCase,
      CreateUserUseCase createUserUseCase) {
    this.idGenerator = idGenerator;
    this.buildUserNameService = buildUserNameService;
    this.loadUsersUseCase = loadUsersUseCase;
    this.createUserUseCase = createUserUseCase;
  }

  @GetMapping("/myusers")
  public List<User> allUsers() {
    return loadUsersUseCase.loadAll().getUsers();
  }

  @PostMapping("/myusers")
  public User createUser(@RequestBody User user) {
    return createUserUseCase
        .create(CreateUserUseCase.CreateUserRequest.builder().name(user.getName()).build())
        .getUser();
  }
}
