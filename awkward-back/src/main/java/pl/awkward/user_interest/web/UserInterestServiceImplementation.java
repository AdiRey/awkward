package pl.awkward.user_interest.web;

import org.springframework.stereotype.Service;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.user_interest.model_repo.UserInterest;
import pl.awkward.user_interest.model_repo.UserInterestRepository;

import java.util.Optional;

@Service
public class UserInterestServiceImplementation implements UserInterestService {
    private final UserInterestRepository userInterestRepository;

    public UserInterestServiceImplementation(UserInterestRepository userInterestRepository) {
        this.userInterestRepository = userInterestRepository;
    }

    @Override
    public void acceptableIds(Long userId, Long interestId) {
        Optional<UserInterest> optional = this.userInterestRepository.findByUserIdAndInterestId(userId, interestId);
        optional.ifPresent(
                e -> {
                    throw new DuplicateException("User has this interest.");
                }
        );
    }

    @Override
    public boolean update(Long id, UserInterest updateUserInterest) {
        Optional<UserInterest> optionalUserInterest = this.userInterestRepository.findById(id);
        if (optionalUserInterest.isEmpty())
            return false;
        UserInterest userInterest = optionalUserInterest.get();
        userInterest.setInterestId(updateUserInterest.getInterestId());
        userInterest.setUserId(updateUserInterest.getUserId());
        return true;
    }
}
