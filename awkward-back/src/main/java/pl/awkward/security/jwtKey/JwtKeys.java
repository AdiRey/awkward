package pl.awkward.security.jwtKey;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class JwtKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String privateKey;

    @Lob
    @Column(nullable = false)
    private String publicKey;

    @Column(nullable = false, unique = true)
    private LocalDate effectiveDate;

}
