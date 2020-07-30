package pl.awkward.message.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private String message;
    private Long fromUserId;
    private LocalDateTime addTime;
}
