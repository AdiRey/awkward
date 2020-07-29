package pl.awkward.liked.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface LikedRepository extends BaseRepository<Liked> {
    Optional<Liked> findByUserIdAndSecondUserId(Long userId, Long secondUserId);
    Page<Liked> findAllByUserIdAndActiveOrderByDateDesc(Long userId, boolean active, Pageable pageable);
}
