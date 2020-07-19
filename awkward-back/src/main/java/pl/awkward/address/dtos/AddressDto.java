package pl.awkward.address.dtos;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String country;
    private String city;
    private Boolean active;
}
