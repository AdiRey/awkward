package pl.awkward.photo.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhotoDto {
    private Long id;
    private String path;
    private LocalDateTime addDate;
    private Boolean archive;
    private Boolean active;
    private Long userId;
}
