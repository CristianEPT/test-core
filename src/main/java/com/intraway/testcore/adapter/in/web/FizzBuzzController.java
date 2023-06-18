package com.intraway.testcore.adapter.in.web;

import com.intraway.testcore.adapter.in.web.model.FizzBuzzResponse;
import com.intraway.testcore.application.port.in.FizzBuzzUseCase;
import com.intraway.testcore.domain.BadRequestException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intraway/api/fizzbuzz")
@RequiredArgsConstructor
@Slf4j
public class FizzBuzzController {

  private final FizzBuzzUseCase fizzBuzzUseCase;
  /**
   * Processes the FizzBuzz for the given range of numbers and returns the FizzBuzz response.
   *
   * @param minNumber The minimum number of the range as a string.
   * @param maxNumber The maximum number of the range as a string.
   * @return The FizzBuzz response containing the processed FizzBuzz details.
   * @throws BadRequestException if the minNumber or maxNumber cannot be parsed as integers.
   */
  @GetMapping("/{min}/{max}")
  public FizzBuzzResponse fizzBuzzProcess(
      @PathVariable("min") String minNumber, @PathVariable("max") String maxNumber) {

    var minNumberValidated = getNumber(minNumber);
    var maxNumberValidated = getNumber(maxNumber);
    var fizzBuzz = fizzBuzzUseCase.fizzBuzzProcess(minNumberValidated, maxNumberValidated);
    return buildSuccessResponse(fizzBuzz.description(), fizzBuzz.code(), fizzBuzz.list());
  }

  private Integer getNumber(String number) {
    try {
      return Integer.parseInt(number);
    } catch (Exception e) {
      log.error("{} is invalid number", number, e);
      throw new BadRequestException(number + " is invalid number");
    }
  }

  private FizzBuzzResponse buildSuccessResponse(String description, String code, String list) {
    return FizzBuzzResponse.builder()
        .code(code)
        .description(description)
        .timestamp(Instant.now().toEpochMilli())
        .list(list)
        .build();
  }
}
