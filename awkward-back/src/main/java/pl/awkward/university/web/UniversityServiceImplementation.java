package pl.awkward.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.university.model_repo.University;
import pl.awkward.university.model_repo.UniversityRepository;

import java.util.Optional;

@Service
public class UniversityServiceImplementation implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImplementation(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Page<University> getAllWithFilter(int page, int size, String column, String direction, String filter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        return this.universityRepository
                .findAllByNameContainingIgnoreCase(filter, PageRequest.of(page, size, sort));
    }

    @Override
    public void acceptableNameAndAddress(final String name, final Long addressId) {
        Optional<University> optionalUniversity = this.universityRepository.findByNameAndAddressId(name, addressId);
        optionalUniversity.ifPresent(
                u -> {
                    throw new DuplicateException("There is a university with this name and place: "
                            + u.getName() + " - " + u.getAddressId());
                }
        );
    }

    @Override
    public boolean update(final Long id, final University updateUniversity) {
        Optional<University> optionalUniversity = this.universityRepository.findById(id);
        if (optionalUniversity.isEmpty())
            return false;
        University university = optionalUniversity.get();
        university.setName(updateUniversity.getName());
        university.setAddressId(updateUniversity.getAddressId());
        return true;
    }
}