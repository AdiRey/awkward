package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardConverter extends BaseConverter<BusinessCard, BusinessCardDto> {
    @Override
    public Function<BusinessCardDto, BusinessCard> toEntity() {
        return dto -> {
            BusinessCard businessCard = new BusinessCard();
            convertIfNotNull(businessCard::setId, dto::getId);
            convertIfNotNull(businessCard::setPhoneNumber, dto::getPhoneNumber);
            convertIfNotNull(businessCard::setFacebookUrl, dto::getFacebookUrl);
            convertIfNotNull(businessCard::setInstUrl, dto::getInstUrl);
            convertIfNotNull(businessCard::setSnapName, dto::getSnapName);
            convertIfNotNull(businessCard::setUserId, dto::getUserId);
            convertIfNotNull(businessCard::setActive, dto::getActive);
            return businessCard;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardDto> toDto() {
        return businessCard -> {
            BusinessCardDto dto = new BusinessCardDto();
            convertIfNotNull(dto::setId, businessCard::getId);
            convertIfNotNull(dto::setPhoneNumber, businessCard::getPhoneNumber);
            convertIfNotNull(dto::setFacebookUrl, businessCard::getFacebookUrl);
            convertIfNotNull(dto::setInstUrl, businessCard::getInstUrl);
            convertIfNotNull(dto::setSnapName, businessCard::getSnapName);
            convertIfNotNull(dto::setUserId, businessCard::getUserId);
            convertIfNotNull(dto::setActive, businessCard::getActive);
            return dto;
        };
    }
}
