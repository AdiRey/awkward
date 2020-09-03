package pl.awkward.message.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.awkward.message.dtos.MessageCreateDto;
import pl.awkward.message.dtos.MessageDto;
import pl.awkward.message.model_repo.Message;
import pl.awkward.message.model_repo.MessageRepository;
import pl.awkward.pair.model_repo.PairRepository;
import pl.awkward.shared.BaseConverter;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PairRepository pairRepository;
    private final MessageRepository messageRepository;
    private final BaseConverter<Message, MessageCreateDto> messageCreateConverter;
    private final BaseConverter<Message, MessageDto> messageConverter;

    @MessageMapping("/messages")
    public void sendTo(@Valid final MessageCreateDto dto, MultipartFile file, @RequestParam Long sendTo) {
        System.out.println("message: " + dto + "; to: " + sendTo);

        Message message = this.messageCreateConverter.toEntity().apply(dto);

        try {
            message.setAddTime(LocalDateTime.now());
            if (file != null)
                message.setAttachment(file.getBytes());
            message.setPair(this.pairRepository.findByTopic(dto.getTopic()).get());

            System.out.println(message);

            this.messageRepository.save(message);

            this.simpMessagingTemplate
                    .convertAndSend(
                            "/messages?sendTo=" + sendTo,
                            this.messageConverter.toDto().apply(this.messageRepository.save(message))
                    );
        } catch (IOException e) {
            System.out.println("---ER---");
        } catch (NoSuchElementException ex) {
            System.out.println("---NoSuch---");
        }
    }
}

