package com.intraway.testcore.application.port.in;

import com.intraway.testcore.domain.FizzBuzz;

public interface PersistencePort {

    void saveFizzBuzz(FizzBuzz fizzBuzz);

}
