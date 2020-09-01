package pl.awkward.interest.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.BaseRepository;

public interface InterestRepository extends BaseRepository<Interest> {

    Page<Interest> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Interest> findAllByNameContainingIgnoreCaseAndActiveIsTrue(String name, Pageable pageable);

}
