package pl.awkward.businessCard.web;

import pl.awkward.businessCard.model_repo.BusinessCard;

public interface BusinessCardService {
    boolean update(Long id, BusinessCard updateBusinessCard);
}
