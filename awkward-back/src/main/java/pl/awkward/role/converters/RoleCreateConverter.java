package pl.awkward.role.converters;

import org.springframework.stereotype.Service;
import pl.awkward.role.dtos.RoleCreateUpdateDto;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class RoleCreateConverter extends BaseConverter<Role, RoleCreateUpdateDto> {
    @Override
    public Function<RoleCreateUpdateDto, Role> toEntity() {
        return dto -> {
            Role role = new Role();
            convertIfNotNull(role::setName, dto::getName);
            convertIfNotNull(role::setStatus, dto::getStatus);
            return role;
        };
    }

    @Override
    public Function<Role, RoleCreateUpdateDto> toDto() {
        return role -> {
            RoleCreateUpdateDto dto = new RoleCreateUpdateDto();
            convertIfNotNull(dto::setName, role::getName);
            convertIfNotNull(dto::setStatus, role::getStatus);
            return dto;
        };
    }
}
