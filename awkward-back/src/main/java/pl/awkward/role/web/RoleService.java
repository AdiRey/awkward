package pl.awkward.role.web;

import pl.awkward.role.model_repo.Role;

public interface RoleService {
    void acceptableName(String name);
    boolean update(Long id, Role updateRole);
}
