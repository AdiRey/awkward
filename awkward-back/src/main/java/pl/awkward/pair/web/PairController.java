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
public class PairController{

    private final BaseConverter<Pair, PairDto> pairConverter;
    private final BaseConverter<Pair, PairCreateDto> pairCreateConverter;
    private final PairService pairService;


    public PairController(
                          BaseConverter<Pair, PairDto> pairConverter,
                          BaseConverter<Pair, PairCreateDto> pairCreateConverter,
                          PairService pairService) {
        this.pairConverter = pairConverter;
        this.pairCreateConverter = pairCreateConverter;
        this.pairService = pairService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PairDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                @RequestParam(defaultValue = "20") final int size,
                                                @RequestParam(defaultValue = "id") final String column,
                                                @RequestParam(defaultValue = "ASC") final String direction) {
//        return super.getAll(page, size, column, direction, this.pairConverter.toDto());
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PairDto> getOne(@PathVariable final Long id) {
//        return super.getOneByActiveTrue(id, this.pairConverter.toDto());
        return null;
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid PairCreateDto dto) {
//        this.pairService.acceptableIds(dto.getUserIdFirst(), dto.getUserIdSecond());
//        return super.create(dto, this.pairCreateConverter.toEntity());
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
//        return super.delete(id);
        return null;
    }

}
