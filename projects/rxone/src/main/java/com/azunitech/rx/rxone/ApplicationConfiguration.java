package com.azunitech.rx.rxone;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
class ApplicationConfiguration extends AbstractReactiveMongoConfiguration {
  private final Environment environment;

  public ApplicationConfiguration(Environment environment) {
    this.environment = environment;
  }

  @Bean
  @DependsOn("embeddedMongoServer")
  public MongoClient mongoClient() {
    int port = environment.getProperty("local.mongo.port", Integer.class);
    return MongoClients.create(String.format("mongodb://localhost:%d", port));
  }

  @Override
  protected String getDatabaseName() {
    return "reactive-mongo";
  }

  @Bean
  @DependsOn("embeddedMongoServer")
  public ReactiveMongoTemplate getTemplate() {
    return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
  }
}
