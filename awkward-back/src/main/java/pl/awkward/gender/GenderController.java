package pl.awkward.gender;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.awkward.shared.BaseConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genders")
@RequiredArgsConstructor
public class GenderController {

    private final GenderRepository genderRepository;
    private final BaseConverter<Gender, GenderDto> genderConverter;

    @GetMapping("")
    public ResponseEntity<List<GenderDto>> getAll() {
        return ResponseEntity.ok(
                this.genderRepository
                        .findAll()
                        .stream()
                        .map(this.genderConverter.toDto())
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderDto> getOne(@PathVariable final Long id) {
        Optional<Gender> optionalGender = this.genderRepository.findById(id);
        return optionalGender
                .map(g -> ResponseEntity.ok(genderConverter.toDto().apply(g)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
