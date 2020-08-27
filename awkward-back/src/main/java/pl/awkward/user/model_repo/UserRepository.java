package pl.awkward.user.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository <User> {
    Optional<User> findFirstByEmailOrUsername(String email, String username);
    @Query(
            value = "SELECT u.* FROM user u " +
                    "WHERE (LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%') OR LOWER(u.surname) LIKE CONCAT('%',LOWER(:surname),'%'))" +
                    "AND u.active IS true",
            countQuery = "SELECT COUNT(*) FROM user u " +
                    "WHERE (LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%') OR LOWER(u.surname) LIKE CONCAT('%',LOWER(:surname),'%'))" +
                    "AND u.active IS true", //it is optional, use it otherwise change 'u.*' to '*' in the main query
            nativeQuery = true
    )
    Page<User> findAllByNameOrSurnameContainsAndActiveIsTrue(@Param("name") String name, @Param("surname") String surname, Pageable pageable);

    @Query(
            value = "SELECT COUNT(*) FROM User",
            nativeQuery = true
    )
    Integer getCount();
}
