package microservices.book.socialmultiplication.service;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.domain.User;
import microservices.book.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.socialmultiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class MultiplicationServiceTest {

    private MultiplicationService multiplicationService;

    @Mock private RandomGeneratorService randomGeneratorService;
    @Mock private MultiplicationResultAttemptRepository attemptRepository;
    @Mock private UserRepository userRepository;

    @BeforeEach
    void setup (){
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(
            randomGeneratorService,
            attemptRepository,
            userRepository);
    }

    @Test
    void createRandomMultiplicationTest (){
        //given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        //when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        //then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(50 * 30);
    }

    @Test
    void checkCorrectAttemptTest (){
        //given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);

        //when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);

        //then
        assertThat(attemptResult).isTrue();
    }

    @Test
    void checkWrongAttemptTest (){
        //given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);

        //when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);

        //then
        assertThat(attemptResult).isFalse();
    }

    @Test
    void retrieveStatsTest (){
        //given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John_doe");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);

        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("john_doe")).willReturn(latestAttempts);

        //when
        List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationService.getStatsForUser("john_doe");

        //then
        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
    }

}
