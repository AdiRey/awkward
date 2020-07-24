package pl.awkward.liked.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikedDto {
    private Long id;
    private Byte status;
    private LocalDateTime date;
    private Boolean active;

    private Long userId;
    private Long secondUserId;
}
