package pl.awkward.user.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository <User> {
    Optional<User> findFirstByEmailOrLogin(String email, String login);
    Page<User> findAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname, Pageable pageable);
}
