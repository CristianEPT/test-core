package com.intraway.testcore.adapter.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FizzBuzzResponse {

  private final Long timestamp;
  private final String code;
  private final String description;
  private final String list;
}
