package com.intraway.testcore.application.services;

import com.intraway.testcore.application.port.in.FizzBuzzUseCase;
import com.intraway.testcore.application.port.in.PersistencePort;
import com.intraway.testcore.domain.BadRequestException;
import com.intraway.testcore.domain.FizzBuzz;
import java.util.List;
import java.util.stream.IntStream;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FizzBuzzService implements FizzBuzzUseCase {
  private static final String FIZZ = "Fizz";
  private static final String BUZZ = "Buzz";
  private final PersistencePort persistencePort;

  /**
   * Processes the given range of numbers and returns a FizzBuzz object with the results.
   *
   * @param minNumber The minimum number of the range (inclusive).
   * @param maxNumber The maximum number of the range (inclusive).
   * @return The FizzBuzz object with the processing results.
   * @throws IllegalArgumentException if minNumber is greater than maxNumber.
   */
  @Override
  public FizzBuzz fizzBuzzProcess(@NonNull Integer minNumber, @NonNull Integer maxNumber) {
    log.debug("Input numbers are min {} and max {}", minNumber, maxNumber);
    isSmallerThan(minNumber, maxNumber);
    var fizzBuzzResultList =
        IntStream.range(minNumber, maxNumber + 1).mapToObj(this::evaluateNumber).toList();
    var fizzBuzz = buildFizzBuzz(minNumber, maxNumber, fizzBuzzResultList);
    saveFizzBuzz(fizzBuzz);
    return fizzBuzz;
  }

  /**
   * Validates if the evaluated number is smaller than the comparison number.
   *
   * @param evaluateNumber The number to be evaluated.
   * @param comparisonNumber The number to compare against.
   * @throws BadRequestException if the evaluated number is greater than the comparison number.
   */
  private void isSmallerThan(Integer evaluateNumber, Integer comparisonNumber) {
    if (evaluateNumber > comparisonNumber)
      throw new BadRequestException(
          String.format("%s is most bigger than %s", evaluateNumber, comparisonNumber));
  }

  /**
   * Evaluates the given number and returns the corresponding FizzBuzz result.
   *
   * @param number The number to evaluate.
   * @return The FizzBuzz result for the given number.
   */
  private String evaluateNumber(int number) {
    if (isMultipleOfThreeAndFive(number)) return FIZZ + BUZZ;
    if (isMultipleOfThree(number)) return FIZZ;
    if (isMultipleOfFive(number)) return BUZZ;
    return String.valueOf(number);
  }

  /**
   * Checks if the given number is a multiple of both 3 and 5.
   *
   * @param number The number to check.
   * @return {@code true} if the number is a multiple of both 3 and 5, {@code false} otherwise.
   */
  private boolean isMultipleOfThreeAndFive(Integer number) {
    return isMultipleOfThree(number) && isMultipleOfFive(number);
  }

  /**
   * Checks if the given number is a multiple of 3.
   *
   * @param number The number to check.
   * @return {@code true} if the number is a multiple of 3 , {@code false} otherwise.
   */
  private boolean isMultipleOfThree(Integer number) {
    return number % 3 == 0;
  }

  /**
   * Checks if the given number is a multiple of 5.
   *
   * @param number The number to check.
   * @return {@code true} if the number is a multiple of 5, {@code false} otherwise.
   */
  private boolean isMultipleOfFive(Integer number) {
    return number % 5 == 0;
  }

  /**
   * Builds a FizzBuzz object using the provided parameters and FizzBuzz results.
   *
   * @param minNumber The minimum number in the range.
   * @param maxNumber The maximum number in the range.
   * @param fizzBuzzResult The list of FizzBuzz results.
   * @return The constructed FizzBuzz object.
   */
  private FizzBuzz buildFizzBuzz(
      Integer minNumber, Integer maxNumber, List<String> fizzBuzzResult) {
    var resultFizzBuzz = mapListToString(fizzBuzzResult);
    var fizzBuzzDetail = getDetail(resultFizzBuzz);
    return new FizzBuzz(
        minNumber, maxNumber, fizzBuzzDetail.description(), fizzBuzzDetail.code(), resultFizzBuzz);
  }

  /**
   * Converts a list of strings into a single string joined by commas.
   *
   * @param resultList The list of strings to be converted.
   * @return The resulting string with the elements of the list joined by commas.
   */
  private String mapListToString(List<String> resultList) {
    return String.join(",", resultList);
  }

  /**
   * Gets the FizzBuzz detail based on the given FizzBuzz result.
   *
   * @param fizzBuzzResult The FizzBuzz result to analyze.
   * @return The FizzBuzz detail corresponding to the given result.
   */
  private FizzBuzzDetail getDetail(String fizzBuzzResult) {
    if (fizzBuzzResult.contains(FIZZ) && fizzBuzzResult.contains(BUZZ))
      return new FizzBuzzDetail("001", "se encontraron múltiplos de 3 y de 5");
    if (fizzBuzzResult.contains(FIZZ))
      return new FizzBuzzDetail("002", "se encontraron múltiplos de 3");
    if (fizzBuzzResult.contains(BUZZ))
      return new FizzBuzzDetail("003", "se encontraron múltiplos de 5");
    return new FizzBuzzDetail("004", "no se encontraron múltiplos de ninguno");
  }

  /**
   * Saves the FizzBuzz object to the persistence layer.
   *
   * @param fizzBuzz The FizzBuzz object to be saved.
   */
  private void saveFizzBuzz(FizzBuzz fizzBuzz) {
    log.debug("saving fizz buzz {} ", fizzBuzz);
    try {
      persistencePort.saveFizzBuzz(fizzBuzz);
    } catch (Exception e) {
      log.error("failed save fizz buzz {} ", fizzBuzz, e);
    }
  }
}
