package pl.awkward.user_address.web;

import pl.awkward.user_address.model_repo.UserAddress;

public interface UserAddressService {
    void acceptableIds(Long userId, Long addressId);
    boolean update(Long id, UserAddress updateUserAddress);
}
