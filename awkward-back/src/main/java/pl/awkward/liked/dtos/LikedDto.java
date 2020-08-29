package pl.awkward.liked.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikedDto {

    private Long firstUserId;

    private Long secondUserId;

    private Byte firstStatus;

    private Byte secondStatus;

}
