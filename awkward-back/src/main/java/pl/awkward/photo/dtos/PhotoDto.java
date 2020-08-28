package pl.awkward.photo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.awkward.address.model_repo.Address;

import java.time.LocalDateTime;

@Data
public class PhotoDto {

    private Long id;
    private String path;

    private Integer position;

    private Boolean archive;

    private LocalDateTime addDate;

    private Long addressId;

    @JsonIgnore
    private Address address;
}
