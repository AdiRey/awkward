package pl.awkward.role.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.role.model_repo.Role;
import pl.awkward.role.model_repo.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

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
