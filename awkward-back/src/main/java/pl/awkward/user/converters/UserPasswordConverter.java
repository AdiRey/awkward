package pl.awkward.user.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.dtos.UserPasswordDto;

import java.util.function.Function;

@Service
public class UserPasswordConverter extends BaseConverter<User, UserPasswordDto> {
    @Override
    public Function<UserPasswordDto, User> toEntity() {
        return dto -> {
            User user = new User();
            convertIfNotNull(user::setPassword, dto::getPassword);
            return user;
        };
    }

    @Override
    public Function<User, UserPasswordDto> toDto() {
        return user -> {
            UserPasswordDto dto = new UserPasswordDto();
            convertIfNotNull(dto::setPassword, user::getPassword);
            return dto;
        };
    }
}
