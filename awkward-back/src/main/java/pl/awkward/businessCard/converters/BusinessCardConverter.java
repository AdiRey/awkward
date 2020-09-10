package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardConverter extends BaseConverter<BusinessCard, BusinessCardDto> {

    @Override
    public Function<BusinessCardDto, BusinessCard> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            BusinessCard card = new BusinessCard();

            card.setId(dto.getUserId());
            card.setPhoneNumber(dto.getPhoneNumber());
            card.setFacebookUrl(dto.getFacebookUrl());
            card.setInstUrl(dto.getInstUrl());
            card.setSnapName(dto.getSnapName());
            card.setActive(dto.getActive());
            card.setAddDate(dto.getAddDate());
            card.setDeleteDate(dto.getDeleteDate());

            return card;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardDto> toDto() {
        return card -> {
            if (card == null)
                return null;

            BusinessCardDto dto = new BusinessCardDto();

            dto.setUserId(card.getId());
            dto.setPhoneNumber(card.getPhoneNumber());
            dto.setFacebookUrl(card.getFacebookUrl());
            dto.setInstUrl(card.getInstUrl());
            dto.setSnapName(card.getSnapName());
            dto.setActive(card.getActive());
            dto.setAddDate(card.getAddDate());
            dto.setDeleteDate(card.getDeleteDate());

            return dto;
        };
    }
}
