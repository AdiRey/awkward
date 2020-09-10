package pl.awkward.university.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.baseStuff.BaseRepository;

public interface UniversityRepository extends BaseRepository<University> {

    Page<University> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<University> findAllByNameContainingIgnoreCaseAndActiveIsTrue(String name, Pageable pageable);

}
