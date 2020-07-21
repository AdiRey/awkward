package pl.awkward.role.model_repo;

import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByName(String name);
}
