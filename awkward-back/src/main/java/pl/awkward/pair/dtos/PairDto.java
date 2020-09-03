package pl.awkward.pair.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PairDto {

    private Long firstUserId;

    private Long secondUserId;

    private LocalDateTime addDate;

    private String topic;

    private Byte status;

}
