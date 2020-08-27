package pl.awkward.user.dtos;

import lombok.Data;
import pl.awkward.gender.GenderDto;
import pl.awkward.interest.dtos.InterestDto;
import pl.awkward.role.dtos.RoleDto;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.user_address.dtos.UserAddressDto;

import java.util.List;

@Data
public class UserShowDto {

    private Long id;
    private String username;

    private String name;
    private String surname;
    private Integer age;
    private String description;

    private GenderDto gender;
    private RoleDto role;
    private UniversityDto university;
    private List<UserAddressDto> userAddresses;
    private List<InterestDto> interests;

}
