package pl.awkward.businessCard.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.businessCard.model_repo.BusinessCardRepository;

import java.util.Optional;

@Service
public class BusinessCardServiceImplementation implements BusinessCardService {

    private final BusinessCardRepository businessCardRepository;

    public BusinessCardServiceImplementation(BusinessCardRepository businessCardRepository) {
        this.businessCardRepository = businessCardRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean update(final Long id, final BusinessCard updateBusinessCard) {
        Optional<BusinessCard> optionalBusinessCard = this.businessCardRepository.findById(id);

        if (optionalBusinessCard.isEmpty())
            return false;

        BusinessCard businessCard = optionalBusinessCard.get();

        businessCard.setPhoneNumber(updateBusinessCard.getPhoneNumber());
        businessCard.setFacebookUrl(updateBusinessCard.getFacebookUrl());
        businessCard.setInstUrl(updateBusinessCard.getInstUrl());
        businessCard.setSnapName(businessCard.getSnapName());

        return true;
    }
}
