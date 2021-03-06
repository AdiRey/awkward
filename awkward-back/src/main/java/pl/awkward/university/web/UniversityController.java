package pl.awkward.university.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.university.dtos.UniversityShowDto;
import pl.awkward.university.model_repo.University;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/universities")
public class UniversityController extends BaseCrudController<University> {

    private final BaseConverter<University, UniversityDto> universityConverter;

    private final BaseConverter<University, UniversityShowDto> universityShowConverter;

    private final UniversityService universityService;


    public UniversityController(final BaseRepository<University> universityRepository,
                                final BaseConverter<University, UniversityDto> universityConverter,
                                final BaseConverter<University, UniversityShowDto> universityShowConverter,
                                final UniversityService universityService) {
        super(universityRepository);
        this.universityConverter = universityConverter;
        this.universityShowConverter = universityShowConverter;
        this.universityService = universityService;
    }

    /* ### GET ### */

    @GetMapping("")
    public ResponseEntity<Page<UniversityShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                          @RequestParam(defaultValue = "20") final int size,
                                                          @RequestParam(defaultValue = "id") final String column,
                                                          @RequestParam(defaultValue = "ASC") final String direction,
                                                          @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAllByActiveTrue(page, size, column, direction, this.universityShowConverter.toDto());
        return ResponseEntity.ok(
                this.universityService.getAllWithFilterByActiveTrue(page, size, column, direction, filter)
                        .map(this.universityShowConverter.toDto())
        );
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<UniversityDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
                                                          @RequestParam(defaultValue = "20") final int size,
                                                          @RequestParam(defaultValue = "id") final String column,
                                                          @RequestParam(defaultValue = "ASC") final String direction,
                                                          @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAll(page, size, column, direction, this.universityConverter.toDto());
        return ResponseEntity.ok(
                this.universityService.getAllWithFilter(page, size, column, direction, filter)
                        .map(this.universityConverter.toDto())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.universityShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<UniversityDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.universityConverter.toDto());
    }

    /* ### POST ### */

    @PostMapping("")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ResponseEntity<Void> create(@RequestBody @Valid final University university) {
        return super.create(university);
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final University university) {
        boolean status = this.universityService.update(id, university);
        return super.update(status);
    }
}
