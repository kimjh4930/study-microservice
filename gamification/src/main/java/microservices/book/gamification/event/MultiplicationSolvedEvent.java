package microservices.book.gamification.event;

import java.io.Serializable;
import java.util.Objects;

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
