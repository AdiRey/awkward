package pl.awkward.pair.web;

import org.springframework.data.domain.Page;
import pl.awkward.pair.model_repo.Pair;

import java.util.Optional;

public interface PairService {

    Page<Pair> getAll(int page, int size);

    Page<Pair> getAllByUserId(Long userId, int page, int size);

    Pair save(Pair pair);

    void delete(Pair pair);

    Optional<Pair> findByTopicUUID(String uuid);

}
