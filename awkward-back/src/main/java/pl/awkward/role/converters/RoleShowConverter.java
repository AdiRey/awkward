package pl.awkward.role.converters;

import org.springframework.stereotype.Service;
import pl.awkward.role.dtos.RoleShowDto;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class RoleShowConverter extends BaseConverter<Role, RoleShowDto> {

    @Override
    public Function<RoleShowDto, Role> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Role role = new Role();

            role.setId(dto.getId());
            role.setName(dto.getName());
            role.setStatus(dto.getStatus());

            return role;
        };
    }

    @Override
    public Function<Role, RoleShowDto> toDto() {
        return role -> {
            if (role == null)
                return null;

            RoleShowDto dto = new RoleShowDto();

            dto.setId(role.getId());
            dto.setName(role.getName());
            dto.setStatus(role.getStatus());

            return dto;
        };
    }

}
