package pl.awkward.pair.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.model_repo.LikedRepository;
import pl.awkward.pair.dtos.PairCreateDto;
import pl.awkward.pair.dtos.PairDto;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.shared.baseStuff.BaseConverter;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pairs")
@RequiredArgsConstructor
public class PairController{

    private final BaseConverter<Pair, PairDto> pairConverter;

    private final BaseConverter<Pair, PairCreateDto> pairCreateConverter;

    private final PairService pairService;

    private final LikedRepository likedRepository;


    /* ### GET ### */


    @GetMapping("")
    public ResponseEntity<Page<PairDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                @RequestParam(defaultValue = "20") final int size) {
        return ResponseEntity.ok(this.pairService.getAll(page, size).map(this.pairConverter.toDto()));
    }

    @GetMapping("/g/{uuid}")
    public ResponseEntity<PairDto> getOne(@PathVariable String uuid) {
        Optional<Pair> optional = this.pairService.findByTopicUUID(uuid);
        return optional
                .map(e -> ResponseEntity.ok(this.pairConverter.toDto().apply(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<Page<PairDto>> getAllByUserId(@PathVariable final Long userId,
                                          @RequestParam(defaultValue = "0") final int page,
                                          @RequestParam(defaultValue = "20") final int size) {
        return ResponseEntity.ok(this.pairService.getAllByUserId(userId, page, size).map(this.pairConverter.toDto()));
    }


    /* ### POST ### */


    @PostMapping("")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ResponseEntity<Void> create(@RequestBody @Valid PairCreateDto dto) {
        Optional<Liked> optionalLiked =
                this.likedRepository.findById_FirstUserIdAndId_SecondUserId(dto.getFirstUserId(), dto.getSecondUserId());

        if (optionalLiked.isEmpty())
            throw new IllegalArgumentException("Nie można utworzyć pary bez lajków");

        Liked liked = optionalLiked.get();

        if (liked.getFirstStatus() == null || liked.getSecondStatus() == null)
            throw new IllegalArgumentException("Brak relacji dwustronnej.");

        Pair pair = this.pairCreateConverter.toEntity().apply(dto);

        pair.setTopic(UUID.randomUUID().toString());
        pair.setAddDate(LocalDateTime.now());
        pair.setLiked(liked);

        Pair saved = this.pairService.save(pair);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/g/{uuid}")
                .buildAndExpand(saved.getTopic()).toUri();

        return ResponseEntity.created(location).build();

    }


    /* ### DELETE ### */


    @DeleteMapping("/g/{uuid}")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<Void> delete(@PathVariable final String uuid) {
        Optional<Pair> optionalPair = this.pairService.findByTopicUUID(uuid);

        if (optionalPair.isEmpty())
            return ResponseEntity.notFound().build();

        this.pairService.delete(optionalPair.get());

        return ResponseEntity.noContent().build();
    }

}
