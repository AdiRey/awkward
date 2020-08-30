package pl.awkward.role.web;

import pl.awkward.role.model_repo.Role;

public interface RoleService {

    boolean update(Long id, Role updateRole);

}
