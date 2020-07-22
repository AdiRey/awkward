package pl.awkward.user_interest.web;

import pl.awkward.user_interest.model_repo.UserInterest;

public interface UserInterestService {
    void acceptableIds(Long userId, Long interestId);
    boolean update(Long id, UserInterest updateUserInterest);
}
