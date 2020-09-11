package pl.awkward.interest.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.shared.baseStuff.BaseRepository;

public interface InterestRepository extends BaseRepository<Interest> {

    @Transactional(readOnly = true)
    Page<Interest> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Interest> findAllByNameContainingIgnoreCaseAndActiveIsTrue(String name, Pageable pageable);

}
