package com.azunitech.gw1.second.persistent.model;

import com.azunitech.gw1.second.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDocument {
  @Id private String id;
  private String name;

  public User toDomain() {
    return User.builder().id(id).name(name).build();
  }

  public void fromDomain(User user) {
    this.id = user.getId();
    this.name = user.getName();
  }
}
