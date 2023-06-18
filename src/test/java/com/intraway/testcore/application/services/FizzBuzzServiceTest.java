package com.intraway.testcore.application.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.intraway.testcore.application.port.in.PersistencePort;
import com.intraway.testcore.domain.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FizzBuzzServiceTest {

  @Mock PersistencePort persistencePort;

  @InjectMocks FizzBuzzService fizzBuzzService;

  @Test
  void givenTwoCorrectNumbersMultipleOf3_ThenReturnFizzBuzz() {

    var fizzBuzz = fizzBuzzService.fizzBuzzProcess(1, 3);
    assertThat(fizzBuzz).isNotNull();
    assertThat(fizzBuzz.code()).isNotNull();
    assertThat(fizzBuzz.code()).isEqualTo("002");
    assertThat(fizzBuzz.description()).isNotNull();
    assertThat(fizzBuzz.description()).contains("3");
    assertThat(fizzBuzz.list()).isNotNull();
    assertThat(fizzBuzz.list()).contains("1,2,Fizz");
  }

  @Test
  void givenTwoCorrectNumbersMultipleOf5_ThenReturnFizzBuzz() {

    var fizzBuzz = fizzBuzzService.fizzBuzzProcess(4, 5);
    assertThat(fizzBuzz).isNotNull();
    assertThat(fizzBuzz.code()).isNotNull();
    assertThat(fizzBuzz.code()).isEqualTo("003");
    assertThat(fizzBuzz.description()).isNotNull();
    assertThat(fizzBuzz.description()).contains("5");
    assertThat(fizzBuzz.list()).isNotNull();
    assertThat(fizzBuzz.list()).contains("4,Buzz");
  }

  @Test
  void givenTwoCorrectNumbersMultipleOf3And5_ThenReturnFizzBuzz() {

    var fizzBuzz = fizzBuzzService.fizzBuzzProcess(14, 15);
    assertThat(fizzBuzz).isNotNull();
    assertThat(fizzBuzz.code()).isNotNull();
    assertThat(fizzBuzz.code()).isEqualTo("001");
    assertThat(fizzBuzz.description()).isNotNull();
    assertThat(fizzBuzz.description()).contains("3 y de 5");
    assertThat(fizzBuzz.list()).isNotNull();
    assertThat(fizzBuzz.list()).contains("14,FizzBuzz");
  }

  @Test
  void givenTwoCorrectNumbersNoHaveMultiple_ThenReturnFizzBuzz() {

    var fizzBuzz = fizzBuzzService.fizzBuzzProcess(1, 2);
    assertThat(fizzBuzz).isNotNull();
    assertThat(fizzBuzz.code()).isNotNull();
    assertThat(fizzBuzz.code()).isEqualTo("004");
    assertThat(fizzBuzz.description()).isNotNull();
    assertThat(fizzBuzz.description()).contains("ninguno");
    assertThat(fizzBuzz.list()).isNotNull();
    assertThat(fizzBuzz.list()).contains("1,2");
  }

  @Test
  void givenOneCorrectNumberAndOneIncorrectNumber_ThenReturnException() {

    assertThrows(NullPointerException.class, () -> fizzBuzzService.fizzBuzzProcess(1, null));
  }

  @Test
  void givenOneIncorrectNumberAndOneCorrectNumber_ThenReturnException() {

    assertThrows(NullPointerException.class, () -> fizzBuzzService.fizzBuzzProcess(null, 3));
  }

  @Test
  void givenTwoCorrectNumbers_WhenMinNumberIsBiggerThanMaxNumber_ThenReturnException() {

    assertThrows(BadRequestException.class, () -> fizzBuzzService.fizzBuzzProcess(5, 3));
  }
}
