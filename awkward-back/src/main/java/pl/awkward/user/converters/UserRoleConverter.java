package pl.awkward.user.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.dtos.UserRoleDto;
import pl.awkward.user.model_repo.User;

import java.util.function.Function;

@Service
public class UserRoleConverter extends BaseConverter<User, UserRoleDto> {

    @Override
    public Function<UserRoleDto, User> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            User user = new User();

            user.setRole(dto.getRole());

            return user;
        };
    }

    @Override
    public Function<User, UserRoleDto> toDto() {
        return user -> {
            if (user == null)
                return null;

            UserRoleDto dto = new UserRoleDto();

            dto.setRole(user.getRole());
            dto.setRoleId(user.getRole().getId());

            return dto;
        };
    }
}
