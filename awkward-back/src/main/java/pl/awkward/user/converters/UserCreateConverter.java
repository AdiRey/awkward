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

            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());

            user.setName(dto.getName());
            user.setSurname(dto.getSurname());

            user.setDateOfBirth(dto.getDateOfBirth());
            user.setDescription(dto.getDescription());
            user.setPassword(dto.getPassword());
            user.setGender(dto.getGender());
            user.setRole(dto.getRole());
            user.setAddDate(dto.getAddDate());
            user.setAge(dto.getAge());

            return user;
        };
    }

    @Override
    public Function<User, UserCreateDto> toDto() {
        return user -> {
            UserCreateDto dto = new UserCreateDto();

            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());

            dto.setName(user.getName());
            dto.setSurname(user.getSurname());

            dto.setDateOfBirth(user.getDateOfBirth());
            dto.setDescription(user.getDescription());
            dto.setPassword(user.getPassword());
            dto.setGender(user.getGender());
            dto.setRole(user.getRole());
            dto.setAddDate(user.getAddDate());
            dto.setAge(user.getAge());

            return dto;
        };
    }
}
