package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardCreateDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardCreateConverter extends BaseConverter<BusinessCard, BusinessCardCreateDto> {

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
            card.setUser(dto.getUser());
            card.setId(dto.getUser().getId());

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
            dto.setUser(card.getUser());

            dto.setUserId(card.getId());

            return dto;
        };
    }
}
