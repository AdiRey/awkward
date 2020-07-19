package pl.awkward.university.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;
import pl.awkward.university.dtos.UniversityCreateUpdateDto;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.university.model_repo.University;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/universities")
public class UniversityController extends BaseCrudController<University> {

    private final BaseConverter<University, UniversityDto> universityConverter;
    private final BaseConverter<University, UniversityCreateUpdateDto> universityCreateUpdateConverter;
    private final UniversityService universityService;

    public UniversityController(BaseRepository<University> universityRepository,
                                BaseConverter<University, UniversityDto> universityConverter,
                                BaseConverter<University, UniversityCreateUpdateDto> universityCreateUpdateConverter,
                                UniversityService universityService) {
        super(universityRepository);
        this.universityService = universityService;
        this.universityConverter = universityConverter;
        this.universityCreateUpdateConverter = universityCreateUpdateConverter;
    }

    @GetMapping("")
    public ResponseEntity<Page<UniversityDto>> getAll(@RequestParam(defaultValue = "0") final int page,
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
    public ResponseEntity<UniversityDto> getOne(@PathVariable final Long id) {
        return super.getOne(id, this.universityConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final UniversityCreateUpdateDto dto) {
        this.universityService.acceptableNameAndAddress(dto.getName(), dto.getAddressId());
        return super.create(dto, this.universityCreateUpdateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final UniversityCreateUpdateDto dto) {
        this.universityService.acceptableNameAndAddress(dto.getName(), dto.getAddressId());
        boolean status = this.universityService.update(id, this.universityCreateUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
