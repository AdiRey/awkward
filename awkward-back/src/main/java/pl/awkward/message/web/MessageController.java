package pl.awkward.message.web;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import pl.awkward.message.dtos.MessageCreateDto;
import pl.awkward.message.dtos.MessageDto;
import pl.awkward.message.model_repo.Message;
import pl.awkward.message.model_repo.MessageRepository;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.pair.model_repo.PairRepository;
import pl.awkward.shared.BaseConverter;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PairRepository pairRepository;
    private final MessageRepository messageRepository;
    private final BaseConverter<Message, MessageCreateDto> messageCreateConverter;
    private final BaseConverter<Message, MessageDto> messageConverter;

    @MessageMapping("/messages/{toUserId}")
    public void sendTo(@DestinationVariable final Long toUserId, final MessageCreateDto dto) {
        System.out.println("message: " + dto + "; to: " + toUserId);

        /*Optional<Pair> optionalPair = this.pairRepository.findByIdAndActiveIsTrue(toUserId);
        if (optionalPair.isEmpty())
            throw new IllegalArgumentException("This pair doesn't exists.");*/

        Message message = this.messageCreateConverter.toEntity().apply(dto);
        message.setAddTime(LocalDateTime.now());
//        message.setPairId(optionalPair.get().getId());
        message.setPairId(toUserId);

        this.simpMessagingTemplate
                .convertAndSend(
                        "/q/messages/" + toUserId,
                        this.messageConverter.toDto().apply(this.messageRepository.save(message))
                );
    }
}

