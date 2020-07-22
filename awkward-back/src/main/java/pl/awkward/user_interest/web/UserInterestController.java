package pl.awkward.user_interest.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;
import pl.awkward.user_interest.dtos.UserInterestCreateDto;
import pl.awkward.user_interest.dtos.UserInterestDto;
import pl.awkward.user_interest.model_repo.UserInterest;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/userInterests")
public class UserInterestController extends BaseCrudController<UserInterest> {

    private final BaseConverter<UserInterest, UserInterestDto> uiConverter;
    private final BaseConverter<UserInterest, UserInterestCreateDto> uiCreateConverter;
    private final UserInterestService userInterestService;


    public UserInterestController(BaseRepository<UserInterest> repository,
                                  BaseConverter<UserInterest, UserInterestDto> uiConverter,
                                  BaseConverter<UserInterest, UserInterestCreateDto> uiCreateConverter,
                                  UserInterestService userInterestService) {
        super(repository);
        this.uiConverter = uiConverter;
        this.uiCreateConverter = uiCreateConverter;
        this.userInterestService = userInterestService;
    }

    @GetMapping("")
    public ResponseEntity<Page<UserInterestDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                    @RequestParam(defaultValue = "20") final int size,
                                                    @RequestParam(defaultValue = "id") final String column,
                                                    @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, uiConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInterestDto> getOne(@PathVariable(name = "id") final Long id) {
        return super.getOne(id, uiConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final UserInterestCreateDto dto) {
        this.userInterestService.acceptableIds(dto.getUserId(), dto.getInterestId());
        return super.create(dto, uiCreateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid UserInterestCreateDto dto) {
        boolean status = this.userInterestService.update(id, uiCreateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
