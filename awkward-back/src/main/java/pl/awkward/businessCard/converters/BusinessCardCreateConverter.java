package pl.awkward.businessCard.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardCreateDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.model_repo.UserRepository;

import javax.persistence.EntityManager;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BusinessCardCreateConverter extends BaseConverter<BusinessCard, BusinessCardCreateDto> {

    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Override
    public Function<BusinessCardCreateDto, BusinessCard> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            BusinessCard card = new BusinessCard();

            card.setPhoneNumber(dto.getPhoneNumber());
            card.setFacebookUrl(dto.getFacebookUrl());
            card.setInstUrl(dto.getInstUrl());
            card.setSnapName(dto.getSnapName());
            card.setUser(this.entityManager.getReference(User.class, dto.getUserId()));

            return card;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardCreateDto> toDto() {
        return card -> {
            if (card == null)
                return null;

            BusinessCardCreateDto dto = new BusinessCardCreateDto();

            dto.setPhoneNumber(card.getPhoneNumber());
            dto.setFacebookUrl(card.getFacebookUrl());
            dto.setInstUrl(card.getInstUrl());
            dto.setSnapName(card.getSnapName());
            dto.setUserId(card.getId());

            return dto;
        };
    }
}
