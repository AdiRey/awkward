package pl.awkward.user_address.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.user_address.model_repo.UserAddress;
import pl.awkward.user_address.model_repo.UserAddressRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImplementation implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean update(final Long userId, final Long addressId, final UserAddress updateUserAddress) {

        Optional<UserAddress> optional = this.userAddressRepository.findByUserIdAndAddressId(userId, addressId);

        if (optional.isEmpty())
            return false;

        UserAddress userAddress = optional.get();

        userAddress.setPosition(updateUserAddress.getPosition());
        userAddress.setTimeInPercentage(updateUserAddress.getTimeInPercentage());

        return true;
    }

    @Override
    public List<UserAddress> getAllByUserId(Long userId) {
        return this.userAddressRepository.findAllByUserId(userId);
    }

    @Override
    public boolean delete(Long userId, Long addressId) {
        Optional<UserAddress> optional = this.userAddressRepository.findByUserIdAndAddressId(userId, addressId);

        if (optional.isEmpty())
            return false;

        this.userAddressRepository.delete(optional.get());

        return true;
    }
}
