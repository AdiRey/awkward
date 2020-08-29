package pl.awkward.businessCard.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardShowDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.model_repo.User;

import javax.persistence.EntityManager;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BusinessCardShowConverter extends BaseConverter<BusinessCard, BusinessCardShowDto> {

    private final EntityManager entityManager;

    @Override
    public Function<BusinessCardShowDto, BusinessCard> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            BusinessCard card = new BusinessCard();

            card.setId(card.getId());
            card.setUser(this.entityManager.getReference(User.class, dto.getUserId()));
            card.setPhoneNumber(dto.getPhoneNumber());
            card.setFacebookUrl(dto.getFacebookUrl());
            card.setInstUrl(dto.getInstUrl());
            card.setSnapName(dto.getSnapName());

            return card;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardShowDto> toDto() {
        return card -> {
            if (card == null)
                return null;

            BusinessCardShowDto dto = new BusinessCardShowDto();

            dto.setUserId(card.getId());
            dto.setPhoneNumber(card.getPhoneNumber());
            dto.setFacebookUrl(card.getFacebookUrl());
            dto.setInstUrl(card.getInstUrl());
            dto.setSnapName(card.getSnapName());

            return dto;
        };
    }
}
