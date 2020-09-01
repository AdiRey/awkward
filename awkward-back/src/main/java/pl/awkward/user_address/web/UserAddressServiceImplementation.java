package pl.awkward.user_address.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.user_address.model_repo.UserAddress;
import pl.awkward.user_address.model_repo.UserAddressRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImplementation implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Override
    public boolean update(Long id, UserAddress updateUserAddress) {
//        Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findById(id);
//        if (optionalUserAddress.isEmpty())
//            return false;
//        UserAddress userAddress = optionalUserAddress.get();
//        userAddress.setUserId(updateUserAddress.getUserId());
//        userAddress.setAddressId(updateUserAddress.getAddressId());
        return true;
    }

}
