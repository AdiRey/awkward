package pl.awkward.photo.dtos;

import lombok.Data;

import javax.persistence.Column;

@Deprecated
@Data
public class PhotoUpdateDto {
    private Integer position;
    @Column(unique = true, nullable = false)
    private String path;
    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;
    private Long userId;
}
