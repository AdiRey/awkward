package pl.awkward.businessCard.converters;

import org.springframework.stereotype.Service;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.businessCard.dtos.BusinessCardUpdateDto;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class BusinessCardUpdateConverter extends BaseConverter<BusinessCard, BusinessCardUpdateDto> {
    @Override
    public Function<BusinessCardUpdateDto, BusinessCard> toEntity() {
        return dto -> {
            BusinessCard businessCard = new BusinessCard();
            convertIfNotNull(businessCard::setPhoneNumber, dto::getPhoneNumber);
            convertIfNotNull(businessCard::setFacebookUrl, dto::getFacebookUrl);
            convertIfNotNull(businessCard::setInstUrl, dto::getInstUrl);
            convertIfNotNull(businessCard::setExactAddress, dto::getExactAddress);
            return businessCard;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardUpdateDto> toDto() {
        return businessCard -> {
            BusinessCardUpdateDto dto = new BusinessCardUpdateDto();
            convertIfNotNull(dto::setPhoneNumber, businessCard::getPhoneNumber);
            convertIfNotNull(dto::setFacebookUrl, businessCard::getFacebookUrl);
            convertIfNotNull(dto::setInstUrl, businessCard::getInstUrl);
            convertIfNotNull(dto::setExactAddress, businessCard::getExactAddress);
            return dto;
        };
    }
}
