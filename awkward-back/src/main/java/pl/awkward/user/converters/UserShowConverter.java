package pl.awkward.user.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.dtos.UserShowDto;
import pl.awkward.user.model_repo.User;

import java.util.function.Function;

@Service
public class UserShowConverter extends BaseConverter<User, UserShowDto> {
    @Override
    public Function<UserShowDto, User> toEntity() {
        return dto -> {
            User user = new User();
            convertIfNotNull(user::setId, dto::getId);
            convertIfNotNull(user::setName, dto::getName);
            convertIfNotNull(user::setSurname, dto::getSurname);
            convertIfNotNull(user::setAge, dto::getAge);
            convertIfNotNull(user::setDescription, dto::getDescription);

            convertIfNotNull(user::setGenderId, dto::getGenderId);
            convertIfNotNull(user::setRoleId, dto::getRoleId);
            convertIfNotNull(user::setUniversityId, dto::getUniversityId);
            return user;
        };
    }

    @Override
    public Function<User, UserShowDto> toDto() {
        return user -> {
            UserShowDto dto = new UserShowDto();
            convertIfNotNull(dto::setId, user::getId);
            convertIfNotNull(dto::setName, user::getName);
            convertIfNotNull(dto::setSurname, user::getSurname);
            convertIfNotNull(dto::setAge, user::getAge);
            convertIfNotNull(dto::setDescription, user::getDescription);

            convertIfNotNull(dto::setGenderId, user::getGenderId);
            convertIfNotNull(dto::setRoleId, user::getRoleId);
            convertIfNotNull(dto::setUniversityId, user::getUniversityId);
            return dto;
        };
    }
}
