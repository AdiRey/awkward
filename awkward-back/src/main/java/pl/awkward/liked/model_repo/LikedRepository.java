package pl.awkward.liked.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikedRepository extends JpaRepository<Liked, Long> {

    Page<Liked> findAllByFirstUserOrSecondUserId(Long firstUserId, Long secondUserId, Pageable pageable);

}
