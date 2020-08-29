package pl.awkward.interest.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.interest.model_repo.InterestRepository;

import java.util.Optional;

@Service
public class InterestServiceImplementation implements InterestService{
    private final InterestRepository interestRepository;

    public InterestServiceImplementation(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    @Override
    public Page<Interest> getAllWithFilter(int page, int size, String column, String direction, String filter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        return this.interestRepository
                .findAllByNameContainingIgnoreCase(filter, PageRequest.of(page, size, sort));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean update(final Long id, final Interest updateInterest) {
        Optional<Interest> optionalInterest = this.interestRepository.findById(id);
        if (optionalInterest.isEmpty())
            return false;

        Interest interest = optionalInterest.get();

        interest.setName(updateInterest.getName());

        return true;
    }
}
