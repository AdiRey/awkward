package pl.awkward.shared.baseStuff;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseCrudController<E extends BaseEntity> {

    private final BaseRepository<E> repository;

    protected <T> ResponseEntity<Page<T>> getAllByActiveTrue(final int page,
                                                     final int size,
                                                     final String column,
                                                     final String direction,
                                                     final Function<E, T> converter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        Page<E> entityPage = this.repository.findAllByActiveIsTrue(PageRequest.of(page, size, sort));
        return ResponseEntity.ok(entityPage.map(converter));
    }

    protected <T> ResponseEntity<Page<T>> getAll(final int page,
                                                     final int size,
                                                     final String column,
                                                     final String direction,
                                                     final Function<E, T> converter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        Page<E> entityPage = this.repository.findAll(PageRequest.of(page, size, sort));
        return ResponseEntity.ok(entityPage.map(converter));
    }


    protected <T> ResponseEntity<T> getOneByActiveTrue(final Long id, Function<E, T> converter) {
        Optional<E> optional = this.repository.findByIdAndActiveIsTrue(id);
        return optional
                .map(e -> ResponseEntity.ok(converter.apply(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    protected <T> ResponseEntity<T> getOne(final Long id, Function<E, T> converter) {
        Optional<E> optional = this.repository.findById(id);
        return optional
                .map(e -> ResponseEntity.ok(converter.apply(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @Transactional
    protected <T> ResponseEntity<Void> create(final E e) {
        e.setAddDate(LocalDateTime.now());
        E saved = this.repository.save(e);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    protected ResponseEntity<Void> update(final boolean status) {
        return status ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    protected ResponseEntity<Void> delete(final Long id) {
        Optional<E> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            E e = optional.get();
            if (!e.getActive())
                return ResponseEntity.notFound().build();
            e.setDeleteDate(LocalDateTime.now());
            e.setActive(false);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
