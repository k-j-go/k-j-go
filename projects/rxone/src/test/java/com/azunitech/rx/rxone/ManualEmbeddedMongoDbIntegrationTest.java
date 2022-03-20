package com.azunitech.rx.rxone;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/*
https://www.javatips.net/api/de.flapdoodle.embed.mongo.config.imongodconfig
 */
public class ManualEmbeddedMongoDbIntegrationTest {
  private static final String CONNECTION_STRING = "mongodb://%s:%d";

  private MongodExecutable mongodExecutable;
  private MongoTemplate mongoTemplate;

  @After
  public void clean() {
    mongodExecutable.stop();
  }

  @Before
  public void setup() throws Exception {
    String ip = "localhost";
    int port = 27017;

    ImmutableMongodConfig mongodConfig =
        MongodConfig.builder()
            .version(Version.Main.PRODUCTION)
            .net(new Net(ip, port, Network.localhostIsIPv6()))
            .build();

    MongodStarter starter = MongodStarter.getDefaultInstance();
    mongodExecutable = starter.prepare(mongodConfig);
    mongodExecutable.start();
    mongoTemplate =
        new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
  }

  @DisplayName(
      "given object to save" + " when save object using MongoDB template" + " then object is saved")
  @Test
  public void test() throws Exception {
    // given
    DBObject objectToSave = BasicDBObjectBuilder.start().add("key", "value").get();

    // when
    mongoTemplate.save(objectToSave, "collection");

    // then
    assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
        .extracting("key")
        .containsOnly("value");
  }
}
