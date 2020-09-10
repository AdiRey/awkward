package pl.awkward.photo.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.baseStuff.BaseRepository;

public interface PhotoRepository extends BaseRepository<Photo> {

    Page<Photo> findAllByUserIdAndArchiveOrderByAddDateDesc(Long userId, Boolean archive, Pageable pageable);

}
