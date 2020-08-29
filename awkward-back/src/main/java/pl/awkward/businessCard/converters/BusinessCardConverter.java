package pl.awkward.businessCard.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.model_repo.User;

import javax.persistence.EntityManager;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BusinessCardConverter extends BaseConverter<BusinessCard, BusinessCardDto> {

    private final EntityManager entityManager;

    @Override
    public Function<BusinessCardDto, BusinessCard> toEntity() {
        return dto -> {
            if (dto == null)
                return null;
            BusinessCard businessCard = new BusinessCard();
            convertIfNotNull(businessCard::setPhoneNumber, dto::getPhoneNumber);
            convertIfNotNull(businessCard::setFacebookUrl, dto::getFacebookUrl);
            convertIfNotNull(businessCard::setInstUrl, dto::getInstUrl);
            convertIfNotNull(businessCard::setSnapName, dto::getSnapName);

            businessCard.setUser(entityManager.getReference(User.class, dto.getUserId()));
//            convertIfNotNull(businessCard::setUserId, dto::getUserId);
            convertIfNotNull(businessCard::setActive, dto::getActive);
            return businessCard;
        };
    }

    @Override
    public Function<BusinessCard, BusinessCardDto> toDto() {
        return businessCard -> {
            if (businessCard == null)
                return null;
            BusinessCardDto dto = new BusinessCardDto();
            convertIfNotNull(dto::setPhoneNumber, businessCard::getPhoneNumber);
            convertIfNotNull(dto::setFacebookUrl, businessCard::getFacebookUrl);
            convertIfNotNull(dto::setInstUrl, businessCard::getInstUrl);
            convertIfNotNull(dto::setSnapName, businessCard::getSnapName);
//            convertIfNotNull(dto::setUserId, businessCard::getUserId);
            convertIfNotNull(dto::setActive, businessCard::getActive);
            return dto;
        };
    }
}
