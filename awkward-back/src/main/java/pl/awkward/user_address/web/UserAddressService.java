package pl.awkward.user_address.web;

import pl.awkward.user_address.model_repo.UserAddress;

public interface UserAddressService {

    boolean update(Long id, UserAddress updateUserAddress);

//    Page<UserAddress> getAll();

//    UserAddress getOne();

//    boolean delete(Long userId, Long addressId);

}
