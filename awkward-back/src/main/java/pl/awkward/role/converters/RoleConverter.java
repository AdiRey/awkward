package pl.awkward.role.converters;

import org.springframework.stereotype.Service;
import pl.awkward.role.dtos.RoleDto;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class RoleConverter extends BaseConverter<Role, RoleDto> {
    @Override
    public Function<RoleDto, Role> toEntity() {
        return dto -> {
            Role role = new Role();
            convertIfNotNull(role::setId, dto::getId);
            convertIfNotNull(role::setName, dto::getName);
            convertIfNotNull(role::setStatus, dto::getStatus);
            return role;
        };
    }

    @Override
    public Function<Role, RoleDto> toDto() {
        return role -> {
            RoleDto dto = new RoleDto();
            convertIfNotNull(dto::setId, role::getId);
            convertIfNotNull(dto::setName, role::getName);
            convertIfNotNull(dto::setStatus, role::getStatus);
            return dto;
        };
    }
}
