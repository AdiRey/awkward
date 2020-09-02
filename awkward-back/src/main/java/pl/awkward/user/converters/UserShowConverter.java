package pl.awkward.user.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.gender.GenderShowConverter;
import pl.awkward.role.converters.RoleShowConverter;
import pl.awkward.shared.BaseConverter;
import pl.awkward.university.converters.UniversityShowConverter;
import pl.awkward.user.dtos.UserShowDto;
import pl.awkward.user.model_repo.User;
import pl.awkward.user_address.converters.UserAddressConverter;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserShowConverter extends BaseConverter<User, UserShowDto> {

    private final RoleShowConverter roleConverter;
    private final GenderShowConverter genderConverter;
    private final UniversityShowConverter universityConverter;
    private final UserAddressConverter userAddressConverter;

    @Override
    public Function<UserShowDto, User> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            User user = new User();

            user.setId(dto.getId());
            user.setUsername(dto.getUsername());

            user.setName(dto.getName());
            user.setSurname(dto.getSurname());
            user.setAge(dto.getAge());
            user.setDescription(dto.getDescription());

            user.setGender(this.genderConverter.toEntity().apply(dto.getGender()));
            user.setRole(this.roleConverter.toEntity().apply(dto.getRole()));
            user.setUniversity(this.universityConverter.toEntity().apply(dto.getUniversity()));
            user.setUserAddresses(
                    dto.getUserAddresses()
                            .stream()
                            .map(this.userAddressConverter.toEntity())
                            .collect(Collectors.toCollection(ArrayList::new))
            );

            return user;
        };
    }

    @Override
    public Function<User, UserShowDto> toDto() {
        return user -> {
            if (user == null)
                return null;

            UserShowDto dto = new UserShowDto();

            dto.setId(user.getId());
            dto.setUsername(user.getUsername());

            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setAge(user.getAge());
            dto.setDescription(user.getDescription());

            dto.setRole(this.roleConverter.toDto().apply(user.getRole()));
            dto.setGender(this.genderConverter.toDto().apply(user.getGender()));
            dto.setUniversity(this.universityConverter.toDto().apply(user.getUniversity()));
            dto.setUserAddresses(
                    user.getUserAddresses()
                            .stream()
                            .map(this.userAddressConverter.toDto())
                            .collect(Collectors.toCollection(ArrayList::new))
            );

            return dto;
        };
    }
}
