package pl.awkward.user_address.model_repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, EmbeddedIds> {

    Optional<UserAddress> findByUserIdAndAddressId(Long userId, Long addressId);

    List<UserAddress> findAllByUserId(Long userId);

}
