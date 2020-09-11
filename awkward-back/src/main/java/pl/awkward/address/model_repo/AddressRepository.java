package pl.awkward.address.model_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.shared.baseStuff.BaseRepository;

public interface AddressRepository extends BaseRepository<Address> {

    @Transactional(readOnly = true)
    Page<Address> findAllByActiveIsTrueAndCountryContainingIgnoreCaseOrActiveIsTrueAndCityContainingIgnoreCase(
            String country,
            String city,
            Pageable pageable
    );

    @Transactional(readOnly = true)
    Page<Address> findAllByCountryContainingIgnoreCaseOrCityContainingIgnoreCase(
            String country,
            String city,
            Pageable pageable
    );
}
