package pl.awkward.interest.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.interest.dtos.InterestDto;
import pl.awkward.interest.dtos.InterestShowDto;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/interests")
public class InterestController extends BaseCrudController<Interest> {

    private final BaseConverter<Interest, InterestDto> interestConverter;

    private final BaseConverter<Interest, InterestShowDto> interestShowConverter;

    private final InterestService interestService;

    public InterestController(final BaseRepository<Interest> repository,
                              final BaseConverter<Interest, InterestDto> interestConverter,
                              final BaseConverter<Interest, InterestShowDto> interestShowConverter,
                              final InterestService interestService) {
        super(repository);
        this.interestConverter = interestConverter;
        this.interestShowConverter = interestShowConverter;
        this.interestService = interestService;
    }

    /* ### GET ### */

    @GetMapping("/allData")
    public ResponseEntity<Page<InterestDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
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

    @GetMapping("")
    public ResponseEntity<Page<InterestShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                    @RequestParam(defaultValue = "20") final int size,
                                                    @RequestParam(defaultValue = "id") final String column,
                                                    @RequestParam(defaultValue = "ASC") final String direction,
                                                    @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAllByActiveTrue(page, size, column, direction, this.interestShowConverter.toDto());
        return ResponseEntity.ok(
                this.interestService.getAllWithFilterByActiveIsTrue(page, size, column, direction, filter)
                        .map(this.interestShowConverter.toDto())
        );
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<InterestDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.interestConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.interestShowConverter.toDto());
    }

    /* ### POST ### */

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final Interest interest) {
        return super.create(interest);
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final Interest interest) {
        boolean status = this.interestService.update(id, interest);
        return super.update(status);
    }
}
