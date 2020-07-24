package pl.awkward.businessCard.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Data
@Entity
public class BusinessCard implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 9)
    private String phoneNumber;
    private String facebookUrl;
    private String instUrl;
    @Column(length = 50)
    private String snapName;
    @Column(nullable = false)
    private Long userId;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
