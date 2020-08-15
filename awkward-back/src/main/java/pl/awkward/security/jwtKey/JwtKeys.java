package pl.awkward.security.jwtKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;

@Data
@Entity
public class JwtKeys {
    @Id
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
