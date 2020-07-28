package pl.awkward.user.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.dtos.UserDto;
import pl.awkward.user.model_repo.User;

import java.util.function.Function;

@Service
public class UserConverter extends BaseConverter<User, UserDto> {

    @Override
    public Function<UserDto, User> toEntity() {
        return dto -> {
            User user = new User();
            convertIfNotNull(user::setId, dto::getId);
            convertIfNotNull(user::setEmail, dto::getEmail);
            convertIfNotNull(user::setLogin, dto::getLogin);
            convertIfNotNull(user::setName, dto::getName);
            convertIfNotNull(user::setSurname, dto::getSurname);
            convertIfNotNull(user::setDateOfBirth, dto::getDateOfBirth);
            convertIfNotNull(user::setAge, dto::getAge);
            convertIfNotNull(user::setDescription, dto::getDescription);
            convertIfNotNull(user::setActive, dto::getActive);

            convertIfNotNull(user::setGenderId, dto::getGenderId);
            convertIfNotNull(user::setRoleId, dto::getRoleId);
            convertIfNotNull(user::setUniversityId, dto::getUniversityId);
            return user;
        };
    }

    @Override
    public Function<User, UserDto> toDto() {
        return user -> {
            UserDto dto = new UserDto();
            convertIfNotNull(dto::setId, user::getId);
            convertIfNotNull(dto::setEmail, user::getEmail);
            convertIfNotNull(dto::setLogin, user::getLogin);
            convertIfNotNull(dto::setName, user::getName);
            convertIfNotNull(dto::setSurname, user::getSurname);
            convertIfNotNull(dto::setDateOfBirth, user::getDateOfBirth);
            convertIfNotNull(dto::setAge, user::getAge);
            convertIfNotNull(dto::setDescription, user::getDescription);
            convertIfNotNull(dto::setActive, user::getActive);

            convertIfNotNull(dto::setGenderId, user::getGenderId);
            convertIfNotNull(dto::setRoleId, user::getRoleId);
            convertIfNotNull(dto::setUniversityId, user::getUniversityId);
            return dto;
        };
    }
}
