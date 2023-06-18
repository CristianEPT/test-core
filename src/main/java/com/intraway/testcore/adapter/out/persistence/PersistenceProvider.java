package com.intraway.testcore.adapter.out.persistence;

import com.intraway.testcore.domain.FizzBuzz;

public interface PersistenceProvider {

    void saveFizzBuzz(FizzBuzz fizzBuzz);

}
