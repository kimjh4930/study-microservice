package microservices.book.gamification.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class GameStats {

    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    public GameStats(Long userId, int score, List<Badge> badges) {
        this.userId = userId;
        this.score = score;
        this.badges = badges;
    }

    public GameStats() {
        this(0L, 0, new ArrayList<>());
    }

    /**
     * 빈 인스턴스 (0점과 배지 없는 상태)를 만들기 위한 팩토리 메소드
     */
    public static GameStats emptyStats (final Long userId){
        return new GameStats(userId, 0, Collections.emptyList());
    }

    public List<Badge> getBadges(){
        return Collections.unmodifiableList(badges);
    }

    public Long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStats gameStats = (GameStats) o;
        return score == gameStats.score &&
            Objects.equals(userId, gameStats.userId) &&
            Objects.equals(badges, gameStats.badges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, score, badges);
    }

    @Override
    public String toString() {
        return "GameStats{" +
            "userId=" + userId +
            ", score=" + score +
            ", badges=" + badges +
            '}';
    }
}
