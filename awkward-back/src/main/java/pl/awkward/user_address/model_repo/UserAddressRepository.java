package pl.awkward.user_address.model_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, EmbeddedIds> {

    Optional<UserAddress> findByUserIdAndAddressId(Long userId, Long addressId);

}
