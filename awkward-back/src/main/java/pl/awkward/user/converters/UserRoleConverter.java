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
            User user = new User();
            convertIfNotNull(user::setRoleId, dto::getRoleId);
            return user;
        };
    }

    @Override
    public Function<User, UserRoleDto> toDto() {
        return user -> {
            UserRoleDto dto = new UserRoleDto();
            convertIfNotNull(dto::setRoleId, user::getRoleId);
            return dto;
        };
    }
}
