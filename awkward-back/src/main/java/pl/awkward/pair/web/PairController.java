package pl.awkward.pair.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.pair.dtos.PairCreateDto;
import pl.awkward.pair.dtos.PairDto;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pairs")
public class PairController extends BaseCrudController<Pair> {

    private final BaseConverter<Pair, PairDto> pairConverter;
    private final BaseConverter<Pair, PairCreateDto> pairCreateConverter;
    private final PairService pairService;


    public PairController(BaseRepository<Pair> repository,
                          BaseConverter<Pair, PairDto> pairConverter,
                          BaseConverter<Pair, PairCreateDto> pairCreateConverter,
                          PairService pairService) {
        super(repository);
        this.pairConverter = pairConverter;
        this.pairCreateConverter = pairCreateConverter;
        this.pairService = pairService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PairDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                @RequestParam(defaultValue = "20") final int size,
                                                @RequestParam(defaultValue = "id") final String column,
                                                @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, this.pairConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PairDto> getOne(@PathVariable final Long id) {
        return super.getOne(id, this.pairConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid PairCreateDto dto) {
//        this.pairService.acceptableIds(dto.getUserIdFirst(), dto.getUserIdSecond());
        return super.create(dto, this.pairCreateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

}
