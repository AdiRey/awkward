package pl.awkward.address.web;

import org.springframework.data.domain.Page;
import pl.awkward.address.model_repo.Address;

public interface AddressService {
    Page<Address> getAllWithFilterAndActiveTrue(int page, int size, String column, String direction, String filter);
    Page<Address> getAllWithFilter(int page, int size, String column, String direction, String filter);
    boolean update(Long id, Address updateAddress);
}
