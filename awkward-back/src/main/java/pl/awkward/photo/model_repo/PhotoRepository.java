package pl.awkward.photo.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface PhotoRepository extends BaseRepository<Photo> {
    Page<Photo> findAllByUserIdAndIsActiveOrderByAddDateDesc(Long userId, Boolean isActive, Pageable pageable);
    Optional<Photo> findByPath(String path);
}
