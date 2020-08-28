package pl.awkward.interest.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.interest.dtos.InterestCreateUpdateDto;
import pl.awkward.interest.dtos.InterestDto;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/interests")
public class InterestController extends BaseCrudController<Interest> {

    private final BaseConverter<Interest, InterestDto> interestConverter;
    private final BaseConverter<Interest, InterestCreateUpdateDto> interestCreateUpdateConverter;
    private final InterestService interestService;

    public InterestController(BaseRepository<Interest> repository,
                              BaseConverter<Interest, InterestDto> interestConverter,
                              BaseConverter<Interest, InterestCreateUpdateDto> interestCreateUpdateConverter,
                              InterestService interestService) {
        super(repository);
        this.interestConverter = interestConverter;
        this.interestCreateUpdateConverter = interestCreateUpdateConverter;
        this.interestService = interestService;
    }

    @GetMapping("")
    public ResponseEntity<Page<InterestDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                    @RequestParam(defaultValue = "20") final int size,
                                                    @RequestParam(defaultValue = "id") final String column,
                                                    @RequestParam(defaultValue = "ASC") final String direction,
                                                    @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAll(page, size, column, direction, this.interestConverter.toDto());
        return ResponseEntity.ok(
                this.interestService.getAllWithFilter(page, size, column, direction, filter)
                        .map(this.interestConverter.toDto())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.interestConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final InterestCreateUpdateDto dto) {
        this.interestService.acceptableName(dto.getName());
        return super.create(dto, this.interestCreateUpdateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final InterestCreateUpdateDto dto) {
        this.interestService.acceptableName(dto.getName());
        boolean status = this.interestService.update(id, interestCreateUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
