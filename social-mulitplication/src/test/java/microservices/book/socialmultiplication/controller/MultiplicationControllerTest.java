package microservices.book.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.service.MultiplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<Multiplication> json;

    @BeforeEach
    void setup (){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void getRandomMultiplicationTest() throws Exception {
        //given
        given(multiplicationService.createRandomMultiplication()).willReturn(new Multiplication(70, 20));

        //when
        MockHttpServletResponse response = mvc.perform(
            get("/multiplications/random")
                .accept(MediaType.APPLICATION_JSON))
            .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(json.write(new Multiplication(70, 20)).getJson());

    }
}