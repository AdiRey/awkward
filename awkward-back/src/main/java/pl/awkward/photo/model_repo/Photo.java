package pl.awkward.photo.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Photo implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String path;
    private LocalDateTime addDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;
    private Long userId;
    private Boolean active;
}
