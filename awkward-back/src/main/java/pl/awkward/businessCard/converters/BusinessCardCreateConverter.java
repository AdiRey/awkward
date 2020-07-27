package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardCreateDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardCreateConverter extends BaseConverter<BusinessCard, BusinessCardCreateDto> {
    @Override
    public Function<BusinessCardCreateDto, BusinessCard> toEntity() {
        return dto -> {
            BusinessCard businessCard = new BusinessCard();
            convertIfNotNull(businessCard::setPhoneNumber, dto::getPhoneNumber);
            convertIfNotNull(businessCard::setFacebookUrl, dto::getFacebookUrl);
            convertIfNotNull(businessCard::setInstUrl, dto::getInstUrl);
            convertIfNotNull(businessCard::setSnapName, dto::getSnapName);
            convertIfNotNull(businessCard::setUserId, dto::getUserId);
            return businessCard;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardCreateDto> toDto() {
        return businessCard -> {
            BusinessCardCreateDto dto = new BusinessCardCreateDto();
            convertIfNotNull(dto::setPhoneNumber, businessCard::getPhoneNumber);
            convertIfNotNull(dto::setFacebookUrl, businessCard::getFacebookUrl);
            convertIfNotNull(dto::setInstUrl, businessCard::getInstUrl);
            convertIfNotNull(dto::setSnapName, businessCard::getSnapName);
            convertIfNotNull(dto::setUserId, businessCard::getUserId);
            return dto;
        };
    }
}
