package pl.awkward.user.dtos;

import lombok.Data;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.gender.GenderDto;
import pl.awkward.role.dtos.RoleDto;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.user_address.dtos.UserAddressDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String username;

    private String name;
    private String surname;
    private Integer age;
    private String description;

    private LocalDate dateOfBirth;
    private LocalDateTime addDate;
    private LocalDateTime deleteDate;

    private Boolean active;
    private Boolean activeNow;

    private RoleDto role;
    private GenderDto gender;
    private UniversityDto university;
    private List<UserAddressDto> userAddresses;
    private BusinessCardDto card;

}
