package pl.awkward.user_interest.model_repo;

import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface UserInterestRepository extends BaseRepository<UserInterest> {
    Optional<UserInterest> findByUserIdAndInterestId(Long userId, Long interestId);
}
