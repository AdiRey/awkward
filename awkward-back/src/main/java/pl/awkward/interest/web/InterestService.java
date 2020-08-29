package pl.awkward.interest.web;

import org.springframework.data.domain.Page;
import pl.awkward.interest.model_repo.Interest;

public interface InterestService {

    Page<Interest> getAllWithFilter(int page, int size, String column, String direction, String filter);

    boolean update(Long id, Interest updateInterest);

}
