package pl.awkward.liked.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LikedRepository extends JpaRepository<Liked, Long> {

    Page<Liked> findAllById_FirstUserIdOrId_SecondUserId(Long firstUserId, Long secondUserId, Pageable pageable);

    Optional<Liked> findById_FirstUserIdAndId_SecondUserId(Long firstUserId, Long secondUserId);

}
