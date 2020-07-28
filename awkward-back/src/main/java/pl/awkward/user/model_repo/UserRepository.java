package pl.awkward.user.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository <User> {
    Optional<User> findFirstByEmailOrLogin(String email, String login);
    @Query(
            value = "SELECT u.* FROM user u " +
                    "WHERE (LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%') OR LOWER(u.surname) LIKE CONCAT('%',LOWER(:surname),'%'))" +
                    "AND u.active IS true",
            countQuery = "SELECT COUNT(*) FROM user u " +
                    "WHERE (LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%') OR LOWER(u.surname) LIKE CONCAT('%',LOWER(:surname),'%'))" +
                    "AND u.active IS true",
            nativeQuery = true
    )
    Page<User> findAllByNameOrSurnameContainsAndActiveIsTrue(@Param("name") String name, @Param("surname") String surname, Pageable pageable);
}
