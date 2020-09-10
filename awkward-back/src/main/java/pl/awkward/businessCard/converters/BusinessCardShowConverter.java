package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardShowDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardShowConverter extends BaseConverter<BusinessCard, BusinessCardShowDto> {

    @Override
    public Function<BusinessCardShowDto, BusinessCard> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            BusinessCard card = new BusinessCard();

            card.setId(dto.getUserId());
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
