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
            if (dto == null)
                return null;

            Role role = new Role();

            role.setName(dto.getName());
            role.setStatus(dto.getStatus());

            return role;
        };
    }

    @Override
    public Function<Role, RoleCreateUpdateDto> toDto() {
        return role -> {
            if (role == null)
                return null;

            RoleCreateUpdateDto dto = new RoleCreateUpdateDto();

            dto.setName(role.getName());
            dto.setStatus(role.getStatus());

            return dto;
        };
    }
}
