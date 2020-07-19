package pl.awkward.address.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.BaseRepository;

import java.util.Optional;

public interface AddressRepository extends BaseRepository<Address> {
    Optional<Address> findByCountryAndCity(String country, String city);
    Page<Address> findAllByCountryContainingIgnoreCaseOrCityContainingIgnoreCase(String country, String city, Pageable pageable);
}
