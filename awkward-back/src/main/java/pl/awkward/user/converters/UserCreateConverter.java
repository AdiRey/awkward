package pl.awkward.user.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.dtos.UserCreateDto;
import pl.awkward.user.model_repo.User;

import java.util.function.Function;

@Service
public class UserCreateConverter extends BaseConverter<User, UserCreateDto> {
    @Override
    public Function<UserCreateDto, User> toEntity() {
        return dto -> {
            User user = new User();
            convertIfNotNull(user::setEmail, dto::getEmail);
            convertIfNotNull(user::setLogin, dto::getLogin);
            convertIfNotNull(user::setName, dto::getName);
            convertIfNotNull(user::setSurname, dto::getSurname);
            convertIfNotNull(user::setDateOfBirth, dto::getDateOfBirth);
            convertIfNotNull(user::setAge, dto::getAge);
            convertIfNotNull(user::setDescription, dto::getDescription);
            convertIfNotNull(user::setPassword, dto::getPassword);

            convertIfNotNull(user::setGenderId, dto::getGenderId);
            convertIfNotNull(user::setRoleId, dto::getRoleId);
            convertIfNotNull(user::setUniversityId, dto::getUniversityId);
            return user;
        };
    }

    @Override
    public Function<User, UserCreateDto> toDto() {
        return user -> {
            UserCreateDto dto = new UserCreateDto();
            convertIfNotNull(dto::setEmail, user::getEmail);
            convertIfNotNull(dto::setLogin, user::getLogin);
            convertIfNotNull(dto::setName, user::getName);
            convertIfNotNull(dto::setSurname, user::getSurname);
            convertIfNotNull(dto::setDateOfBirth, user::getDateOfBirth);
            convertIfNotNull(dto::setAge, user::getAge);
            convertIfNotNull(dto::setDescription, user::getDescription);
            convertIfNotNull(dto::setPassword, user::getPassword);

            convertIfNotNull(dto::setGenderId, user::getGenderId);
            convertIfNotNull(dto::setRoleId, user::getRoleId);
            convertIfNotNull(dto::setUniversityId, user::getUniversityId);
            return dto;
        };
    }
}
