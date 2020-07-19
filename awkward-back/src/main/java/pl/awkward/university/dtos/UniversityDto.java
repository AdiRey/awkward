package pl.awkward.university.dtos;

import lombok.Data;

@Data
public class UniversityDto {
    private Long id;
    private String name;
    private Long addressId;
    private Boolean active;
}
