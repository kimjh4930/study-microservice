package microservices.book.gamification.domain;

import java.util.Objects;

public final class LeaderBoardRow {

    private final Long userId;
    private final Long totalScore;

    public LeaderBoardRow(Long userId, Long totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
    }

    public LeaderBoardRow(){
        this(0L, 0L);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaderBoardRow that = (LeaderBoardRow) o;
        return Objects.equals(userId, that.userId) &&
            Objects.equals(totalScore, that.totalScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, totalScore);
    }

    @Override
    public String toString() {
        return "LeaderBoardRow{" +
            "userId=" + userId +
            ", totalScore=" + totalScore +
            '}';
    }
}
