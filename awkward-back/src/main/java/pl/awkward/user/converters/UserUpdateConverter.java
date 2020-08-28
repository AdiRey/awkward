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
            if (dto == null)
                return null;

            User user = new User();

            user.setEmail(dto.getEmail());
            user.setUsername(dto.getUsername());

            user.setName(dto.getName());
            user.setSurname(dto.getSurname());

            user.setDateOfBirth(dto.getDateOfBirth());
            user.setDescription(dto.getDescription());

            user.setAge(dto.getAge());
            user.setGender(dto.getGender());
            user.setUniversity(dto.getUniversity());

            return user;
        };
    }

    @Override
    public Function<User, UserUpdateDto> toDto() {
        return user -> {
            if (user == null)
                return null;

            UserUpdateDto dto = new UserUpdateDto();

            dto.setEmail(user.getEmail());
            dto.setUsername(user.getUsername());

            dto.setName(user.getName());
            dto.setSurname(user.getSurname());

            dto.setDateOfBirth(user.getDateOfBirth());
            dto.setDescription(user.getDescription());

            dto.setAge(user.getAge());
            dto.setGender(user.getGender());
            dto.setUniversity(user.getUniversity());

            dto.setGenderId(user.getGender().getId());
            dto.setUniversityId(user.getUniversity().getId());

            return dto;
        };
    }
}
