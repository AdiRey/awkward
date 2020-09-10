package pl.awkward.address.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.awkward.shared.baseStuff.BaseRepository;

public interface AddressRepository extends BaseRepository<Address> {

    Page<Address> findAllByActiveIsTrueAndCountryContainingIgnoreCaseOrActiveIsTrueAndCityContainingIgnoreCase(
            String country,
            String city,
            Pageable pageable
    );

    Page<Address> findAllByCountryContainingIgnoreCaseOrCityContainingIgnoreCase(
            String country,
            String city,
            Pageable pageable
    );
}
