package pl.awkward.role.converters;

import org.springframework.stereotype.Service;
import pl.awkward.role.dtos.RoleDto;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class RoleConverter extends BaseConverter<Role, RoleDto> {

    @Override
    public Function<RoleDto, Role> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Role role = new Role();

            role.setId(dto.getId());
            role.setName(dto.getName());
            role.setStatus(dto.getStatus());
            role.setActive(dto.getActive());
            role.setAddDate(dto.getAddDate());
            role.setDeleteDate(dto.getDeleteDate());

            return role;
        };
    }

    @Override
    public Function<Role, RoleDto> toDto() {
        return role -> {
            if (role == null)
                return null;

            RoleDto dto = new RoleDto();

            dto.setId(role.getId());
            dto.setName(role.getName());
            dto.setStatus(role.getStatus());
            dto.setActive(role.getActive());
            dto.setAddDate(role.getAddDate());
            dto.setDeleteDate(role.getDeleteDate());

            return dto;
        };
    }
}
