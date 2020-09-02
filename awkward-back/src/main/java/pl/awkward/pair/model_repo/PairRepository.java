package pl.awkward.pair.model_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.awkward.liked.model_repo.UserIdsKey;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface PairRepository extends JpaRepository<Pair, UserIdsKey> {
//    Optional<Pair> findByUserIdFirstAndUserIdSecond(Long userIdFirst, Long userIdSecond);
}
