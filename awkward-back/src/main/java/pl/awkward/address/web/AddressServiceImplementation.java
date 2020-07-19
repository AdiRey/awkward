package pl.awkward.address.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.awkward.address.model_repo.Address;
import pl.awkward.address.model_repo.AddressRepository;
import pl.awkward.exceptions.DuplicateException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Page<Address> getAllWithFilter(int page, int size, String column, String direction, String filter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        return this.addressRepository
                .findAllByCountryContainingIgnoreCaseOrCityContainingIgnoreCase(filter, filter, PageRequest.of(page, size, sort));
    }

    @Override
    public void acceptableCountryAndCity(String country, String city) {
        Optional<Address> optionalAddress = this.addressRepository.findByCountryAndCity(country, city);
        optionalAddress.ifPresent(
                a -> {
                    throw new DuplicateException("This address already exists: " + a.getCountry() + " - " + a.getCity());
                }
        );
    }

    @Override
    public boolean update(Long id, Address updateAddress) {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            return false;
        Address address = optionalAddress.get();
        address.setCountry(updateAddress.getCountry());
        address.setCity(updateAddress.getCity());
        return true;
    }
}
