package pl.awkward.user_address.web;

import pl.awkward.user_address.model_repo.UserAddress;

import java.util.List;

public interface UserAddressService {

    boolean update(Long userId, Long addressId, UserAddress updateUserAddress);

    List<UserAddress> getAllByUserId(Long userId);

    boolean delete(Long userId, Long addressId);

}
