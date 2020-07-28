package microservices.book.socialmultiplication.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RandomGeneratorServiceTest {

    private RandomGeneratorService randomGeneratorService;

    @Autowired
    public RandomGeneratorServiceTest(RandomGeneratorServiceImpl randomGeneratorServiceImpl) {
        this.randomGeneratorService = randomGeneratorServiceImpl;
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
        List<Integer> randomFactors = IntStream.range(0, 1000)
            .map(i -> randomGeneratorService.generateRandomFactor())
            .boxed()
            .collect(Collectors.toList());

        assertThat(randomFactors).containsAll(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
    }

}
