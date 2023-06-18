package com.intraway.testcore.adapter.in.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.intraway.testcore.adapter.in.web.model.FizzBuzzResponse;
import com.intraway.testcore.application.port.in.FizzBuzzUseCase;
import com.intraway.testcore.domain.FizzBuzz;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest(
    controllers = FizzBuzzController.class,
    properties = "spring.cloud.config.enabled=false")
class FizzBuzzControllerTest {

  private static final String BASE_URL = "/intraway/api/fizzbuzz";

  @Autowired MockMvc mockMvc;

  @MockBean FizzBuzzUseCase fizzBuzzUseCase;

  @Test
  void givenTwoCorrectNumber_ThenReturnFizzBuzzResultSuccessfully() throws Exception {

    var minNumber = 1;
    var maxNumber = 3;

    given(fizzBuzzUseCase.fizzBuzzProcess(1, 3))
        .willReturn(
            new FizzBuzz(minNumber, maxNumber, "se encontraron múltiplos de 3", "002", "1,2,Fizz"));

    var executionResult =
        mockMvc
            .perform(get(BASE_URL + "/1/3").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    then(fizzBuzzUseCase).should(times(1)).fizzBuzzProcess(1, 3);
    var executionResultContent = executionResult.getResponse().getContentAsString();
    assertThat(executionResultContent).isNotNull();
    assertThat(executionResultContent).isNotEmpty();
    var json = new Gson();
    var fizzBuzzResponse = json.fromJson(executionResultContent, FizzBuzzResponse.class);
    assertThat(fizzBuzzResponse).isNotNull();
    assertThat(fizzBuzzResponse.getCode()).isNotNull();
    assertThat(fizzBuzzResponse.getCode()).isEqualTo("002");
    assertThat(fizzBuzzResponse.getList()).isNotNull();
    assertThat(fizzBuzzResponse.getList()).isEqualTo("1,2,Fizz");
  }

  @Test
  void givenOneCorrectNumberAndOneIncorrectNumber_ThenReturnError() throws Exception {

    mockMvc
        .perform(get(BASE_URL + "/999999999999999/3").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    then(fizzBuzzUseCase).should(times(0)).fizzBuzzProcess(Mockito.any(), Mockito.any());
  }
}
