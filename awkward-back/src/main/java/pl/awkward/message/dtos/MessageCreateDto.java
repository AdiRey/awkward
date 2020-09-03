package pl.awkward.message.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MessageCreateDto {

    @NotBlank
    private String topic;

    @NotBlank
    private String message;

    @NotNull
    private Long fromUserId;

}
