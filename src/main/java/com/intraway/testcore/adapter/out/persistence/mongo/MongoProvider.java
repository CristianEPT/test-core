package com.intraway.testcore.adapter.out.persistence.mongo;

import com.intraway.testcore.adapter.out.persistence.PersistenceProvider;
import com.intraway.testcore.adapter.out.persistence.mongo.model.FizzBuzzModel;
import com.intraway.testcore.adapter.out.persistence.mongo.repository.FizzBuzzRepository;
import com.intraway.testcore.domain.FizzBuzz;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MongoProvider implements PersistenceProvider {

  private final FizzBuzzRepository fizzBuzzRepository;

  /**
   * Saves the FizzBuzz object to the persistence layer.
   *
   * @param fizzBuzz The FizzBuzz object to be saved.
   */
  @Override
  public void saveFizzBuzz(FizzBuzz fizzBuzz) {
    fizzBuzzRepository.save(mapDomainToModel(fizzBuzz));
  }

  private FizzBuzzModel mapDomainToModel(FizzBuzz fizzBuzz) {
    return FizzBuzzModel.builder()
        .list(fizzBuzz.list())
        .timestamp(Instant.now().toEpochMilli())
        .description(fizzBuzz.description())
        .code(fizzBuzz.code())
        .build();
  }
}
