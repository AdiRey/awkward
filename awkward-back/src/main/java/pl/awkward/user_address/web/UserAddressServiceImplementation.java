package pl.awkward.user_address.web;

import org.springframework.stereotype.Service;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.user_address.model_repo.UserAddress;
import pl.awkward.user_address.model_repo.UserAddressRepository;

import java.util.Optional;

@Service
public class UserAddressServiceImplementation implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddressServiceImplementation(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public void acceptableIds(Long userId, Long addressId) {
        Optional<UserAddress> optional = this.userAddressRepository.findByUserIdAndAddressId(userId, addressId);
        optional.ifPresent(
                e -> {
                    throw new DuplicateException("User has this address.");
                }
        );
    }

    @Override
    public boolean update(Long id, UserAddress updateUserAddress) {
        Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findById(id);
        if (optionalUserAddress.isEmpty())
            return false;
        UserAddress userAddress = optionalUserAddress.get();
        userAddress.setUserId(updateUserAddress.getUserId());
        userAddress.setAddressId(updateUserAddress.getAddressId());
        return true;
    }

}
