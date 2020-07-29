package microservices.book.socialmultiplication.domain;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
public final class MultiplicationResultAttempt {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
    private final Multiplication multiplication;
    private final int resultAttempt;

    private final boolean correct;

    public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt, boolean correct) {
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }

    MultiplicationResultAttempt (){
        this(null, null, -1, false);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public int getResultAttempt() {
        return resultAttempt;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "MultiplicationResultAttempt{" +
            "id=" + id +
            ", user=" + user +
            ", multiplication=" + multiplication +
            ", resultAttempt=" + resultAttempt +
            ", correct=" + correct +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiplicationResultAttempt that = (MultiplicationResultAttempt) o;
        return resultAttempt == that.resultAttempt &&
            correct == that.correct &&
            Objects.equals(id, that.id) &&
            Objects.equals(user, that.user) &&
            Objects.equals(multiplication, that.multiplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, multiplication, resultAttempt, correct);
    }
}
