package pl.awkward.message.converters;

import org.springframework.stereotype.Service;
import pl.awkward.message.dtos.MessageDto;
import pl.awkward.message.model_repo.Message;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class MessageConverter extends BaseConverter<Message, MessageDto> {
    @Override
    public Function<MessageDto, Message> toEntity() {
        return dto -> {
            Message message = new Message();
            convertIfNotNull(message::setId, dto::getId);
            convertIfNotNull(message::setFromUserId, dto::getFromUserId);
            convertIfNotNull(message::setAddTime, dto::getAddTime);
            convertIfNotNull(message::setMessage, dto::getMessage);
            return message;
        };
    }

    @Override
    public Function<Message, MessageDto> toDto() {
        return message -> {
            MessageDto dto = new MessageDto();
            convertIfNotNull(dto::setId, message::getId);
            convertIfNotNull(dto::setFromUserId, message::getFromUserId);
            convertIfNotNull(dto::setAddTime, message::getAddTime);
            convertIfNotNull(dto::setMessage, message::getMessage);
            return dto;
        };
    }
}
