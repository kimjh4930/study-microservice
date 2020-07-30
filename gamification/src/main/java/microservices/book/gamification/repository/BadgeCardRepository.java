package microservices.book.gamification.repository;

import microservices.book.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
    // 주어진 사용자의 배지 카드를 모두 조회
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
