package microservices.book.socialmultiplication.event;

import java.io.Serializable;
import java.util.Objects;

/**
 *  시스템에서 {@link microservices.book.socialmultiplication.domain.Multiplication}
 *  문제가 해결됐다는 사실을 모델링한 이벤트
 *  곱셈에 대한 컨텍스트 정보르 ㄹ제공
 */
public class MultiplicationSolvedEvent implements Serializable {
    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;

    public MultiplicationSolvedEvent(Long multiplicationResultAttemptId, Long userId, boolean correct) {
        this.multiplicationResultAttemptId = multiplicationResultAttemptId;
        this.userId = userId;
        this.correct = correct;
    }

    public Long getMultiplicationResultAttemptId() {
        return multiplicationResultAttemptId;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiplicationSolvedEvent that = (MultiplicationSolvedEvent) o;
        return correct == that.correct &&
            Objects.equals(multiplicationResultAttemptId, that.multiplicationResultAttemptId) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiplicationResultAttemptId, userId, correct);
    }
}
