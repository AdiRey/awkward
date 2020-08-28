package pl.awkward.address.dtos;

import lombok.Data;

@Data
public class AddressShowDto {
    private Long id;
    private String country;
    private String city;
}
