package pl.awkward.shared.baseStuff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
    Page<E> findAllByActiveIsTrue(Pageable pageable);
    Optional<E> findByIdAndActiveIsTrue(Long id);
}
