package pl.awkward.role.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.role.model_repo.Role;
import pl.awkward.role.model_repo.RoleRepository;

import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void acceptableName(final String name) {
        Optional<Role> optionalRole = this.roleRepository.findByName(name);
        optionalRole.ifPresent(
                role -> {
                    throw new DuplicateException("There is a role with this name: " + role.getName());
                }
        );
    }

    @Override
    @Transactional
    public boolean update(final Long id, final Role updateRole) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        if (optionalRole.isEmpty())
            return false;
        Role role = optionalRole.get();
        role.setName(updateRole.getName());
        role.setStatus(updateRole.getStatus());
        return true;
    }
}
