package com.azunitech.rx.rxtwo.persistent.model;

import com.azunitech.rx.rxtwo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDocument {
  @Id private String id;
  private String name;

  public Product toDomain() {
    return Product.builder().name(name).id(id).build();
  }
}
