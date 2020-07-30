package pl.awkward.message.converters;

import org.springframework.stereotype.Service;
import pl.awkward.message.dtos.MessageCreateDto;
import pl.awkward.message.model_repo.Message;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class MessageCreateConverter extends BaseConverter<Message, MessageCreateDto> {
    @Override
    public Function<MessageCreateDto, Message> toEntity() {
        return dto -> {
            Message message = new Message();
            convertIfNotNull(message::setFromUserId, dto::getFromUserId);
            convertIfNotNull(message::setMessage, dto::getMessage);
            return message;
        };
    }

    @Override
    public Function<Message, MessageCreateDto> toDto() {
        return message -> {
            MessageCreateDto dto = new MessageCreateDto();
            convertIfNotNull(dto::setFromUserId, message::getFromUserId);
            convertIfNotNull(dto::setMessage, message::getMessage);
            return dto;
        };
    }
}
