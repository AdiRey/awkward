package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardUpdateDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardUpdateConverter extends BaseConverter<BusinessCard, BusinessCardUpdateDto> {

    @Override
    public Function<BusinessCardUpdateDto, BusinessCard> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            BusinessCard card = new BusinessCard();

            card.setPhoneNumber(dto.getPhoneNumber());
            card.setFacebookUrl(dto.getFacebookUrl());
            card.setInstUrl(dto.getInstUrl());
            card.setSnapName(dto.getSnapName());

            return card;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardUpdateDto> toDto() {
        return card -> {
            if (card == null)
                return null;

            BusinessCardUpdateDto dto = new BusinessCardUpdateDto();

            dto.setPhoneNumber(card.getPhoneNumber());
            dto.setFacebookUrl(card.getFacebookUrl());
            dto.setInstUrl(card.getInstUrl());
            dto.setSnapName(card.getSnapName());

            return dto;
        };
    }
}
