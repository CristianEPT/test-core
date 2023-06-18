package com.intraway.testcore.application.port.in;

import com.intraway.testcore.domain.FizzBuzz;
import lombok.NonNull;

public interface FizzBuzzUseCase {

  FizzBuzz fizzBuzzProcess(@NonNull Integer minNumber, @NonNull Integer maxNumber);
}
