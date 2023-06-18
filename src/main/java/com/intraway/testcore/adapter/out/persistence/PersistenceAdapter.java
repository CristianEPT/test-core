package com.intraway.testcore.adapter.out.persistence;

import com.intraway.testcore.application.port.in.PersistencePort;
import com.intraway.testcore.domain.FizzBuzz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersistenceAdapter implements PersistencePort {

  private final PersistenceProvider persistenceProvider;

  @Override
  public void saveFizzBuzz(FizzBuzz fizzBuzz) {
    persistenceProvider.saveFizzBuzz(fizzBuzz);
  }
}
