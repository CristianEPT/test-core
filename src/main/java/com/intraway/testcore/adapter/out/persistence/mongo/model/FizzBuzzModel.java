package com.intraway.testcore.adapter.out.persistence.mongo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("FizzBuzz")
public class FizzBuzzModel {

  @Id private String id;
  private Long timestamp;
  private String code;
  private String description;
  private String list;
}
