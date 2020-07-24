package pl.awkward.liked.model_repo;

import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface LikedRepository extends BaseRepository<Liked> {
    Optional<Liked> findByUserIdAndSecondUserId(Long userId, Long secondUserId);
}
