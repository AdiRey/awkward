package pl.awkward.user.dtos;

import lombok.Data;
import pl.awkward.gender.GenderShowDto;
import pl.awkward.role.dtos.RoleShowDto;
import pl.awkward.university.dtos.UniversityShowDto;
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

    private GenderShowDto gender;
    private RoleShowDto role;
    private UniversityShowDto university;
    private List<UserAddressDto> userAddresses;

}
