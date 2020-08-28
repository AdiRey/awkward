package pl.awkward.user.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.businessCard.converters.BusinessCardConverter;
import pl.awkward.gender.GenderConverter;
import pl.awkward.role.converters.RoleConverter;
import pl.awkward.shared.BaseConverter;
import pl.awkward.university.converters.UniversityConverter;
import pl.awkward.user.dtos.UserDto;
import pl.awkward.user.model_repo.User;
import pl.awkward.user_address.converters.UserAddressConverter;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserConverter extends BaseConverter<User, UserDto> {

    private final RoleConverter roleConverter;
    private final GenderConverter genderConverter;
    private final UniversityConverter universityConverter;
    private final UserAddressConverter userAddressConverter;
    private final BusinessCardConverter businessCardConverter;

    @Override
    public Function<UserDto, User> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            User user = new User();

            user.setId(dto.getId());
            user.setEmail(dto.getEmail());
            user.setUsername(dto.getUsername());

            user.setAge(dto.getAge());
            user.setDescription(dto.getDescription());

            user.setName(dto.getName());
            user.setSurname(dto.getSurname());

            user.setDateOfBirth(dto.getDateOfBirth());
            user.setAddDate(dto.getAddDate());
            user.setDeleteDate(dto.getDeleteDate());

            user.setActive(dto.getActive());
            user.setActiveNow(dto.getActiveNow());

            user.setRole(this.roleConverter.toEntity().apply(dto.getRole()));
            user.setGender(this.genderConverter.toEntity().apply(dto.getGender()));
            user.setUniversity(this.universityConverter.toEntity().apply(dto.getUniversity()));
            user.setUserAddresses(
                    dto.getUserAddresses()
                            .stream()
                            .map(this.userAddressConverter.toEntity())
                            .collect(Collectors.toCollection(ArrayList::new))
            );

            user.setCard(this.businessCardConverter.toEntity().apply(dto.getCard()));

            return user;
        };
    }

    @Override
    public Function<User, UserDto> toDto() {
        return user -> {
            if (user == null)
                return null;

            UserDto dto = new UserDto();

            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setUsername(user.getUsername());

            dto.setAge(user.getAge());
            dto.setDescription(user.getDescription());

            dto.setName(user.getName());
            dto.setSurname(user.getSurname());

            dto.setDateOfBirth(user.getDateOfBirth());
            dto.setAddDate(user.getAddDate());
            dto.setDeleteDate(user.getDeleteDate());

            dto.setActive(user.getActive());
            dto.setActiveNow(user.getActiveNow());

            dto.setRole(this.roleConverter.toDto().apply(user.getRole()));
            dto.setGender(this.genderConverter.toDto().apply(user.getGender()));
            dto.setUniversity(this.universityConverter.toDto().apply(user.getUniversity()));
            dto.setUserAddresses(
                    user.getUserAddresses()
                            .stream()
                            .map(this.userAddressConverter.toDto())
                            .collect(Collectors.toCollection(ArrayList::new))
            );

            dto.setCard(this.businessCardConverter.toDto().apply(user.getCard()));

            return dto;
        };
    }
}
