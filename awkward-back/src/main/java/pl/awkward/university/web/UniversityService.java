package pl.awkward.university.web;

import org.springframework.data.domain.Page;
import pl.awkward.university.model_repo.University;

public interface UniversityService {

    Page<University> getAllWithFilter(int page, int size, String column, String direction, String filter);

    Page<University> getAllWithFilterByActiveTrue(int page, int size, String column, String direction, String filter);

    boolean update(Long id, University updateUniversity);

}
