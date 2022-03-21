package com.azunitech.gw1.second.usecases;

import com.azunitech.gw1.second.application.EventSourceSink;
import com.azunitech.gw1.second.application.IDGenerator;
import com.azunitech.gw1.second.domain.EventSource;
import com.azunitech.gw1.second.domain.User;
import com.azunitech.gw1.second.domain.UserGateway;
import com.azunitech.gw1.second.usecases.api.CreateUserUseCase;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateUserUseCaseImpl implements CreateUserUseCase {
  private final UserGateway userGateway;
  private final IDGenerator idGenerator;
  private final EventSourceSink sink;

  public CreateUserUseCaseImpl(
      UserGateway userGateway, IDGenerator idGenerator, EventSourceSink sink) {
    this.userGateway = userGateway;
    this.idGenerator = idGenerator;
    this.sink = sink;
  }

  @Override
  public CreateUserUseCase.CreateUserResponse create(CreateUserRequest request) {
    String id = idGenerator.createID();
    User newUser = User.builder().name(request.getName()).id(id).build();
    sink.sink(
            EventSource.builder()
                .eventSourceType(EventSource.EventSourceType.CREATE)
                .time(new Date())
                .user(newUser)
                .build())
        .subscribe();
    return CreateUserResponse.builder().user(this.userGateway.create(newUser)).build();
  }
}
