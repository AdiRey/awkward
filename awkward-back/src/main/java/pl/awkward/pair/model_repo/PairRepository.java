package pl.awkward.pair.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.awkward.liked.model_repo.UserIdsKey;

import java.util.Optional;

public interface PairRepository extends JpaRepository<Pair, UserIdsKey> {

    Page<Pair> findAllByLiked_FirstUserIdOrLiked_SecondUserId(Long firstUserId, Long secondUserId, Pageable pageable);

    Optional<Pair> findByTopic(String topic);

}
