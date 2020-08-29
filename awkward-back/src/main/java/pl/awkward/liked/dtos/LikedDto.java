package pl.awkward.liked.dtos;

import lombok.Data;

@Data
public class LikedDto {

    private Long firstUserId;

    private Long secondUserId;

    private Byte firstStatus;

    private Byte secondStatus;

}
