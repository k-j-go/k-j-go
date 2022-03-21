package com.azunitech.gw1.second;

import com.azunitech.gw1.second.application.BuildUserNameService;
import com.azunitech.gw1.second.gateway.BeanConfig;
import com.azunitech.gw1.second.kafka.KafkaConfig;
import com.azunitech.gw1.second.persistent.PersistentConfig;
import com.azunitech.gw1.second.service.ServiceConfig;
import com.azunitech.gw1.second.usecases.UseCaseConfig;
import com.azunitech.gw1.second.usecases.api.CreateUserUseCase;
import com.azunitech.gw1.second.web.WebConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.stream.IntStream;

@SpringBootApplication
@Import({
  KafkaConfig.class,
  PersistentConfig.class,
  ServiceConfig.class,
  WebConfig.class,
  UseCaseConfig.class,
  BeanConfig.class
})
public class FirstApplication {
  final CreateUserUseCase createUserUseCase;
  final BuildUserNameService buildUserNameService;

  public FirstApplication(
      CreateUserUseCase createUserUseCase, BuildUserNameService buildUserNameService) {
    this.createUserUseCase = createUserUseCase;
    this.buildUserNameService = buildUserNameService;
  }

  public static void main(String[] args) {
    SpringApplication.run(FirstApplication.class, args);
  }

  @Bean
  public ApplicationRunner getRunner() {
    return args -> {
      IntStream.range(1, 10)
          .forEach(
              x -> {
                String name = buildUserNameService.buildPersonName();
                createUserUseCase.create(
                    CreateUserUseCase.CreateUserRequest.builder().name(name).build());
              });
    };
  }
}
