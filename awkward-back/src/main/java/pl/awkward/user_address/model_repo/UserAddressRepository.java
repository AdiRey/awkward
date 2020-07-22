package pl.awkward.user_address.model_repo;

import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface UserAddressRepository extends BaseRepository<UserAddress> {
    Optional<UserAddress> findByUserIdAndAddressId(Long userId, Long addressId);
}
