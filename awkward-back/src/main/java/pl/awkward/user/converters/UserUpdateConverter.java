package pl.awkward.user.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.dtos.UserUpdateDto;
import pl.awkward.user.model_repo.User;

import java.util.function.Function;

@Service
public class UserUpdateConverter extends BaseConverter<User, UserUpdateDto> {
    @Override
    public Function<UserUpdateDto, User> toEntity() {
        return dto -> {
            User user = new User();
            convertIfNotNull(user::setEmail, dto::getEmail);
            convertIfNotNull(user::setLogin, dto::getLogin);
            convertIfNotNull(user::setName, dto::getName);
            convertIfNotNull(user::setSurname, dto::getSurname);
            convertIfNotNull(user::setDateOfBirth, dto::getDateOfBirth);
            convertIfNotNull(user::setDescription, dto::getDescription);

            convertIfNotNull(user::setUniversityId, dto::getUniversityId);
            return user;
        };
    }

    @Override
    public Function<User, UserUpdateDto> toDto() {
        return user -> {
            UserUpdateDto dto = new UserUpdateDto();
            convertIfNotNull(dto::setEmail, user::getEmail);
            convertIfNotNull(dto::setLogin, user::getLogin);
            convertIfNotNull(dto::setName, user::getName);
            convertIfNotNull(dto::setSurname, user::getSurname);
            convertIfNotNull(dto::setDateOfBirth, user::getDateOfBirth);
            convertIfNotNull(dto::setDescription, user::getDescription);

            convertIfNotNull(dto::setUniversityId, user::getUniversityId);
            return dto;
        };
    }
}
