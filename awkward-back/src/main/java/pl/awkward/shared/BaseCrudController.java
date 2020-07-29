package pl.awkward.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseCrudController<E extends BaseEntity> {

    private final BaseRepository<E> repository;

    protected <T> ResponseEntity<Page<T>> getAll(final int page,
                                                 final int size,
                                                 final String column,
                                                 final String direction,
                                                 final Function<E, T> converter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        Page<E> entityPage = this.repository.findAllByActiveIsTrue(PageRequest.of(page, size, sort));
        return ResponseEntity.ok(entityPage.map(converter));
    }


    protected <T> ResponseEntity<T> getOne(final Long id, Function<E, T> converter) {
        Optional<E> optional = this.repository.findByIdAndActiveIsTrue(id);
        return optional
                .map(e -> ResponseEntity.ok(converter.apply(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    protected <T> ResponseEntity<Void> create(final T t, Function<T, E> converter) {
        E saved = this.repository.save(converter.apply(t));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    protected ResponseEntity<Void> update(final boolean status) {
        return status ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Transactional
    protected ResponseEntity<Void> delete(final Long id) {
        Optional<E> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            E e = optional.get();
            if (!e.getActive())
                return ResponseEntity.notFound().build();
            e.setActive(false);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
