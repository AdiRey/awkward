package pl.awkward.photo.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;
import pl.awkward.user.model_repo.User;

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
    @Column(nullable = false)
    private LocalDateTime addDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean archive;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Long addressId;

}
