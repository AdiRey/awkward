package pl.awkward.university.dtos;

import lombok.Data;
import pl.awkward.address.dtos.AddressShowDto;

@Data
public class UniversityShowDto {

    private Long id;

    private String name;

    private AddressShowDto address;

}
