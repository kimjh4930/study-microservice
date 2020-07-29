package microservices.book.socialmultiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    // Multiplication 관련 정보를 전달하기 위한 익스체인지
    private String multiplicationExchange;

    // 특정 이벤트를 전송하기 위한 라우팅 키
    private String multiplicationSolvedRoutingKey;

    @Autowired
    public EventDispatcher(
            RabbitTemplate rabbitTemplate,
            @Value("${multiplication.exchange}") final String multiplicationExchange,
            @Value("${multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(
            multiplicationExchange,
            multiplicationSolvedRoutingKey,
            multiplicationSolvedEvent);
    }
}
