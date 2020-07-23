package pl.awkward.pair.model_repo;

import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface PairRepository extends BaseRepository<Pair> {
    Optional<Pair> findByUserIdFirstAndUserIdSecond(Long userIdFirst, Long userIdSecond);
}
