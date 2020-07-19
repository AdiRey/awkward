package pl.awkward.address.web;

import org.springframework.data.domain.Page;
import pl.awkward.address.model_repo.Address;

public interface AddressService {
    Page<Address> getAllWithFilter(int page, int size, String column, String direction, String filter);
    void acceptableCountryAndCity(String country, String city);
    boolean update(Long id, Address updateAddress);
}
